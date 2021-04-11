package com.aarria.retail.core.util;

import com.aarria.retail.core.util.Enum.ImageType;

import java.util.Map;

public class Application {

	public static final String IMAGES_DOMAIN = "/resources/assets/images/resources/";

	public static final String SEPARATOR = "/";

	public static final int THUMBNAIL_HEIGHT = 340;
	public static final int THUMBNAIL_WIDTH = 233;

	public static final String APP_NAME = "https://www.aarria.com";

	public static final String SHELL_SCRIPT_FILE_LOCATION = "/opt/fallsbuy/upload-images.sh";

	public static final String MYSQL_BACKUP_SHELL_SCRIPT_FILE_LOCATION = "/opt/fallsbuy/take-mysql-dump.sh";

	public static final String MYSQL_BACKEDUP_SQL_FILE_LOCATION = "/opt/fallsbuy/backup.sql";

	public static final String CDN_HOST_USERNAME = "root";

	public static final String FIND_CITY_FROM_IP_HOSTNAME = "https://api.ipdata.co/";

	public static final String FIND_ADDRESS_FROM_LAT_LANG = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyBYCltp_IZIxoIiPVc9OycNmtgIPmzTSHg&latlng=";
	
	public static final String FIND_IP_API_KEY = "/city?api-key=459cf4c2da577251e580e7e4329e4397555431cc45439fcf81520ce1";

	public static final Integer INCLUDE_SOLDOUT = 0;

	public static final String DOT = ".";

	public static final Map<ImageType, Integer> SIZES = Map.of(ImageType.PREVIEW, 480, ImageType.SMALL, 80,
			ImageType.THUMBNAIL, 340);
	
}
