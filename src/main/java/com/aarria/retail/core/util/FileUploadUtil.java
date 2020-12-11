package com.aarria.retail.core.util;

import static com.aarria.retail.core.util.Application.SEPARATOR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

@Component
public class FileUploadUtil {

	Logger LOGGER = LogManager.getLogger(FileUploadUtil.class);

	@Autowired
	private AppProperties properties;

	ChannelSftp channelSftp = null;

	private static void copyFilesToAWS(String path) {
		try {
			Runtime rt = Runtime.getRuntime();
			String[] arguments = { Application.SHELL_SCRIPT_FILE_LOCATION, "argument one", "argument two" };
			Process proc = rt.exec(arguments);
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			System.out.println("### " + output);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void copyAllProductImages(Long id) {

		if (channelSftp == null) {
			channelSftp = getChannel();
		}

		LOGGER.debug("Starting copying files to CDN for product id " + id);

		try {
			copyToCDN(properties.getStaticFilesRootPath() + SEPARATOR + id, properties.getStaticFilesRootPath(),
					getChannel());
		} catch (Exception ex) {
			LOGGER.error("Unable to copy uploaded file to static cdn " + ex);
		}
	}

	public void copyFile(String directory, String sourcePath, String destinationPath) {

		if (channelSftp == null) {
			channelSftp = getChannel();
		}

		try {
			copyFileToCDN(directory, sourcePath, destinationPath);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(Util.retrieveStackTraceFromException(ex));
		}
	}

	private ChannelSftp getChannel() {
		try {
			Session session;
			Channel channel;
			ChannelSftp channelSftp;
			JSch jsch = new JSch();
			// jsch.addIdentity(properties.getCdnPrivateKeyLocation());
			session = jsch.getSession(Application.CDN_HOST_USERNAME, properties.getCdnUrl(), 22);
			session.setPassword("Super*Star77");
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			return channelSftp;
		} catch (Exception e) {
			throw new RuntimeException("Unable to get sftp connection..");
		}
	}

	private void copyFileToCDN(String directory, String sourcePath, String destPath)
			throws SftpException, FileNotFoundException {

		File localFile = new File(sourcePath);

		LOGGER.info("Copying file to CDN sourcePath = " + sourcePath + ", destPath = " + destPath + ", sftpChannel = "
				+ channelSftp + " to directory " + directory);

		if (localFile.isFile()) {

			if (!Util.isValidImageFormat(sourcePath)) {
				return;
			}

			// copy if it is a file
			channelSftp.cd(directory);

			if (!localFile.getName().startsWith(".")) {
				channelSftp.put(new FileInputStream(localFile), localFile.getName(), ChannelSftp.OVERWRITE);
			}
		} else {
			throw new RuntimeException(
					"Wrong method called in FileuploadUtil. It's a directory not a file. Why directory paramater is passed instead of file");
		}
	}

	private void copyToCDN(String sourcePath, String destPath, ChannelSftp sftpChannel)
			throws SftpException, FileNotFoundException {

		File localFile = new File(sourcePath);

		LOGGER.info("Copying images to CDN sourcePath = " + sourcePath + ", destPath = " + destPath + ", sftpChannel = "
				+ sftpChannel);

		if (localFile.isFile()) {

			if (!Util.isValidImageFormat(sourcePath)) {
				return;
			}

			// copy if it is a file
			sftpChannel.cd(localFile.getParentFile().getAbsolutePath());

			if (!localFile.getName().startsWith(".")) {
				sftpChannel.put(new FileInputStream(localFile), localFile.getName(), ChannelSftp.OVERWRITE);
			}
		} else {
			File[] files = localFile.listFiles();

			if (files != null && files.length > 0 && !localFile.getName().startsWith(".")) {

				try {
					sftpChannel.cd(destPath);
				} catch (Exception e) {
					LOGGER.debug("cd failed.. making directory " + destPath);
					sftpChannel.mkdir(destPath);
					sftpChannel.cd(destPath);
				}
				SftpATTRS attrs = null;

				// check if the directory is already existing
				try {
					attrs = sftpChannel.stat(destPath + SEPARATOR + localFile.getName());
				} catch (Exception e) {
					LOGGER.debug(destPath + SEPARATOR + localFile.getName() + " not found");
				}

				// else create a directory
				if (attrs != null) {
					LOGGER.debug("Directory exists IsDir=" + attrs.isDir());
				} else {
					LOGGER.debug("Creating dir " + localFile.getName());
					sftpChannel.mkdir(localFile.getName());
				}

				for (int i = 0; i < files.length; i++) {

					copyToCDN(files[i].getAbsolutePath(), destPath + SEPARATOR + localFile.getName(), sftpChannel);

				}

			}
		}
	}

	public void deleteDirectory(String path) throws Exception {

		if (channelSftp == null) {
			channelSftp = getChannel();
		}

		LOGGER.info("Started deleting directory " + path);
		
		try {
			SftpATTRS attrs = channelSftp.lstat(path);
		}catch(Exception e) {
			LOGGER.error("It seems the file does not exist in remote machine " + path);
			return;
		}
		channelSftp.cd(path); // Change Directory on SFTP Server
		// List source directory structure.
		Vector<ChannelSftp.LsEntry> fileAndFolderList = channelSftp.ls(path);

		// Iterate objects in the list to get file/folder names.
		for (ChannelSftp.LsEntry item : fileAndFolderList) {

			// If it is a file (not a directory).
			if (!item.getAttrs().isDir()) {

				channelSftp.rm(path + "/" + item.getFilename()); // Remove file.

			} else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) { // If it is a subdir.

				try {

					// removing sub directory.
					channelSftp.rmdir(path + "/" + item.getFilename());

				} catch (Exception e) { // If subdir is not empty and error occurs.

					// Do lsFolderRemove on this subdir to enter it and clear its contents.
					deleteDirectory(path + "/" + item.getFilename());
				}
			}
		}

		channelSftp.rmdir(path); // delete the parent directory after empty
	}

}
