package com.aarria.retail.core.util;

import com.aarria.retail.core.domain.*;
import com.aarria.retail.core.util.Enum.ImageType;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.BreadcrumbDto;
import com.aarria.retail.web.dto.response.CategoryDto;
import com.aarria.retail.web.dto.response.ProductDetailsImageDto;
import com.lambdaworks.crypto.SCryptUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.attribute.PosixFilePermission;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Util {

    private static Logger LOGGER = LogManager.getLogger(Util.class);

    private static RestTemplate restTemplate = new RestTemplate();

    public enum Sort {

        PRICE_HIGH_TO_LOW("Price: High To Low"), PRICE_LOW_TO_HIGH("Price: Low To High"), DISCOUNT(
                "Discount: High To Low"), WHATS_NEW("What's New");

        String s;

        Sort(String s) {
            this.s = s;
        }

        public String getSort() {
            return s;
        }
    }

    public static String getSortColumn(Sort sort) {

        String column = "addedDate";

        if (sort == null) {
            return column;
        }

        switch (sort) {
            case PRICE_HIGH_TO_LOW:
                column = "price";
                break;
            case PRICE_LOW_TO_HIGH:
                column = "price";
                break;
            case WHATS_NEW:
                column = "addedDate";
                break;
            case DISCOUNT:
                column = "discount";
                break;
        }

        return column;
    }

    public static Set<PosixFilePermission> getDirectoryPermissions() {
        Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
        // add owners permission
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        // add group permissions
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        // add others permissions
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
        return perms;
    }

    public static Direction getSortOrder(Sort sort) {

        Direction column = Direction.DESC;

        if (sort == null) {
            return column;
        }

        switch (sort) {
            case PRICE_HIGH_TO_LOW:
                column = Direction.DESC;
                break;
            case PRICE_LOW_TO_HIGH:
                column = Direction.ASC;
                break;
            case WHATS_NEW:
                column = Direction.DESC;
                break;
            case DISCOUNT:
                column = Direction.DESC;
                break;
            default:
                column = Direction.DESC;
        }

        return column;
    }

    public static boolean isValidImageFormat(String image) {
        if (StringUtils.containsIgnoreCase(image, "jpg") || StringUtils.containsIgnoreCase(image, "png")
                || StringUtils.containsIgnoreCase(image, "jpeg")) {
            return true;
        }

        return false;
    }

    public static String getThumbnailImageName(String fileName, ImageType imageType) {
        return imageType.name().toLowerCase() + Application.DOT + FilenameUtils.getExtension(fileName);
    }

    public static Integer calculateOff(Double price, Double actualPrice) {

        if (price == null || actualPrice == null) {
            return null;
        }

        return (int) Math.floor(100 - ((price / actualPrice) * 100));
    }

    public static ProductDetailsImageDto createProductDetailImages(ProductDetailsImageDto i) {
        // we are storing only thumbnail.jpg in product colors table. we just use the
        // extension and determine the other file names.
        String preview = i.getSmall() == null ? null : i.getSmall().replace("thumbnail", "preview");
        String original = i.getSmall() == null ? null : i.getSmall().replace("thumbnail", "original");

        ProductDetailsImageDto dto = new ProductDetailsImageDto(i.getId(), i.getSmall(), preview, original);
        return dto;
    }

    public static ProductDetailsImageDto createProductDetailImagesFromProductColor(Long productColorId, String image) {
        // we are storing only small.jpg in product colors table. we just use the
        // extension and determine the other file names.
        String preview = image == null ? null : image.replace("small", "preview");
        String original = image == null ? null : image.replace("small", "original");

        ProductDetailsImageDto dto = new ProductDetailsImageDto(productColorId, image, preview, original);
        return dto;
    }

    public static List<BreadcrumbDto> getParents(Category category) {

        List<BreadcrumbDto> breadcrumps = new ArrayList<>();

        if (category != null) {

            BreadcrumbDto bto = new BreadcrumbDto();
            bto.setId(category.getId());
            bto.setName(category.getName());
            breadcrumps.add(bto);

            Category parentCategory = category.getParentCategory();

            if (parentCategory != null) {

                BreadcrumbDto bto1 = new BreadcrumbDto();
                bto1.setId(parentCategory.getId());
                bto1.setName(parentCategory.getName());

                breadcrumps.add(bto1);

                Category grandParentCategory = parentCategory.getParentCategory();

                if (grandParentCategory != null) {

                    BreadcrumbDto bto2 = new BreadcrumbDto();
                    bto2.setId(grandParentCategory.getId());
                    bto2.setName(grandParentCategory.getName());
                    breadcrumps.add(bto2);

                    Category grandGrandParentCategory = grandParentCategory.getParentCategory();

                    if (grandGrandParentCategory != null) {

                        BreadcrumbDto bto3 = new BreadcrumbDto();
                        bto3.setId(grandGrandParentCategory.getId());
                        bto3.setName(grandGrandParentCategory.getName());
                        breadcrumps.add(bto3);
                    }
                }
            }
        }

        java.util.Collections.reverse(breadcrumps);

        return breadcrumps;
    }

    public static String encodePassword(String password) {
        return SCryptUtil.scrypt(password, 16, 16, 16);
    }

    public static boolean decodePassword(String password, String hash) {
        return SCryptUtil.check(password, hash);
    }

    public static String generateImageLocation(String image) {

        if (image == null) {
            return null;
        }

        return Application.IMAGES_DOMAIN + image.trim();
    }

    public static String generateRandomOrderId(long orderId) {
        // LocalDateTime today = LocalDateTime.now();
        // return new
        // StringBuilder().append(orderId).append(today.getYear()).append(today.getMonth().getValue())
        // .append(today.getDayOfYear()) + "";
        return String.format("%05d", orderId);
    }

    public static boolean isEmail(String input) {
        return input.contains("@");
    }

    public static boolean isMobile(String input) {
        return NumberUtils.isNumber(input);
    }

    public static String formatEstimatedDeliveryDate(Order order) {
        ZoneId indiaZone = ZoneId.of(ZoneId.SHORT_IDS.get("IST"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM YYYY");
        LocalDateTime fromDate = convertToLocalDateTimeViaInstant(order.getDelivery().getExpectedDeliveryDate());
        LocalDateTime toDate = fromDate.withHour(0).withMinute(0).withSecond(0).plusDays(1);
        return fromDate.format(formatter) + " - " + toDate.format(formatter);
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Denote a valid item. means.. it is not Returned, Not removed..etc.,
     *
     * @param item
     * @return
     */
    public static boolean isItemPurchased(Item item) {
        return item.getStatus().equals(ItemStatus.OPEN.ordinal());
    }

    public static void printModelAndViewAttributes(ModelAndView view) {
        ModelMap map = view.getModelMap();
        for (String key : map.keySet()) {
            System.out.println("Checkout page model - key = " + key + ", value = " + map.get(key));
        }
    }

    public static boolean isItemsAreEligibleForReturn(Order order) {
        if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
            for (Item item : order.getOrderedItems()) {
                if (item.getStatus().equals(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
                    return false;
                }
            }
        }

        return true;
    }

    public static List<Integer> getReturnStatusForOrders() {
        List<Integer> refundStatus = new ArrayList<>();
        refundStatus.add(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal());
        return refundStatus;
    }

    public static List<Integer> getReturnStatusForItems() {
        List<Integer> refundStatus = new ArrayList<>();
        refundStatus.add(ItemStatus.OPEN.ordinal());
        return refundStatus;
    }

    public static List<Integer> getReturnPickupRequestedStatusInAdminForItems() {
        List<Integer> refundStatus = new ArrayList<>();
        // refundStatus.add(ItemStatus.REFUND_INITIATED.ordinal());
        refundStatus.add(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal());
        return refundStatus;
    }

    public static List<Integer> getCancelAndRefundStatusForItems() {
        List<Integer> refundStatus = new ArrayList<>();
        refundStatus.add(ItemStatus.CANCELLED_WITHIN_30_MINS.ordinal());
        refundStatus.add(ItemStatus.CANCELLED_WHILE_PACKAGING.ordinal());
        refundStatus.add(ItemStatus.REFUNDED.ordinal());
        return refundStatus;
    }

    public static List<Integer> getCancelRefundStatusOrdinalsForOrders() {
        List<Integer> refundStatus = new ArrayList<>();
        refundStatus.add(OrderStatus.REFUND_INITIATED.ordinal());
        return refundStatus;
    }

    public static List<Integer> getCancelRefundStatusOrdinalsForItems() {
        List<Integer> refundStatus = new ArrayList<>();
        refundStatus.add(ItemStatus.REFUND_INITIATED.ordinal());
        return refundStatus;
    }

    public static Integer getReturnCashMethod(Order order) {
        Integer returnCashWay = order.getReturnCashMethod();

        for (Item item : order.getOrderedItems()) {
            if (Util.getCancelRefundStatusOrdinalsForItems().contains(item.getStatus())) {
                if (returnCashWay == null && item.getReturnCashMethod() != null) {
                    returnCashWay = item.getReturnCashMethod();
                    break;// Here we are considering the first return cash method as the return cash
                    // method for this refund
                }
            }
        }
        return returnCashWay;
    }

    public static boolean isValidRefundStatusForOrder(Order order) {
        if (order.getStatus().equals(OrderStatus.REFUNDED.ordinal())) {
            return false;
        } else if (order.getStatus().equals(OrderStatus.REFUND_INITIATED.ordinal())) {
            return true;
        }

        return false;
    }

    public static boolean isItemEligibleForRemove(Order order, Item item) {
        if (order.getStatus().equals(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal())
                || order.getStatus().equals(OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal())
                || order.getStatus().equals(OrderStatus.BEING_PACKED.ordinal())
                || order.getStatus().equals(OrderStatus.DISPATCHED.ordinal())
                || order.getStatus().equals(OrderStatus.DISPATCHED.ordinal())) {
            return false;
        }

        if (item.getStatus() != null && (item.getStatus().equals(ItemStatus.CANCELLED_WITHIN_30_MINS.ordinal()))
                || item.getStatus().equals(ItemStatus.CANCELLED_WHILE_PACKAGING.ordinal())) {
            return false;
        }

        return true;
    }

    public static boolean isValidRefundStatusForItem(Item item) {
        if (item.getStatus().equals(ItemStatus.REFUND_INITIATED.ordinal())) {
            return true;
        }
        return false;
    }

    public static boolean isValidRefundStatusForItems(Order order) {

        if (order.getStatus().equals(OrderStatus.REFUNDED.ordinal())) {
            return false;
        } else {
            if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
                for (Item item : order.getOrderedItems()) {
                    if (item.getStatus().equals(ItemStatus.REFUND_INITIATED.ordinal())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static List<AddressDto> prepareAddresses(List<Address> addresses) {
        List<AddressDto> dtos = new ArrayList<>();
        if (addresses != null && !addresses.isEmpty()) {
            for (Address a : addresses) {
                dtos.add(AddressDto.createDtoFromAddress(a));
            }
        }

        return dtos;
    }

    public static AddressDto deliverHere(List<Address> addresses) {
        if (addresses != null && !addresses.isEmpty()) {
            return AddressDto.createDtoFromAddress(addresses.stream()
                    .filter(a -> a.getIsDeliverHere() != null && a.getIsDeliverHere()).findFirst().orElse(null));
        }

        return null;
    }

    public static String retrieveStackTraceFromException(Exception e) {
        return Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    public static Boolean isShowReturnPickup(Order order) {
        boolean show = false;
        if (order.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())
                || (order.getIsAnyOfTheItemIsReturned() != null && order.getIsAnyOfTheItemIsReturned() == true
                && (order.getIsReturnedItemReceived() == null
                || order.getIsReturnedItemReceived().equals(false)))) {
            show = true;
        }

        return show;
    }

    public static Boolean isShowReturnPickupForAdmin(Order order) {
        boolean show = false;
        if (order.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())
                || (order.getIsAnyOfTheItemIsReturned() != null && order.getIsAnyOfTheItemIsReturned() == true)
                || (order.getIsDelivered() != null && order.getIsDelivered().equals(true)
                && order.getStatus().equals(OrderStatus.REFUND_INITIATED.ordinal()))) {
            show = true;
        }

        return show;
    }

    public static boolean isReturnPickupRequested(Order order) {

        if (order.getIsReturnPickupArranged() != null && order.getIsReturnPickupArranged().equals(true)) {
            return false;
        }

        if (order.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
            return true;
        }

        if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
            for (Item item : order.getOrderedItems()) {
                if (item.getStatus().equals(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String getOrderedProductName(Order order) {
        if(order != null) {
            List<Item> orderedItems = order.getOrderedItems();
            if(CollectionUtils.isNotEmpty(orderedItems)) {
                Product product = orderedItems.get(0).getProduct();
                if(product != null) {
                    return product.getName().substring(0,25) + "...";
                }
            }
        }

        return null;
    }

    public static String getFlowId(Order order, boolean isOTP) {

        if (isOTP) {
            return Enum.OrderStatusToFlowIdMapper.OTP.getValue();
        }

        String flowId = null;

        if (order != null) {
            int itemCount = CollectionUtils.isNotEmpty(order.getOrderedItems()) ? order.getOrderedItems().size() : 0;

            if (order.getStatus() != null && Enum.OrderStatus.OPEN.ordinal() == order.getStatus()) {
                if (itemCount > 1) {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.MULTIPLE_ORDER_CONFIRMED.name());
                } else {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.SINGLE_ORDER_CONFIRMED.name());
                }
            }

            String statusString = OrderStatus.getString(order.getStatus()) != null ? OrderStatus.getString(order.getStatus()) : "";

            if (statusString.contains("CANCEL")) {
                if (itemCount > 1) {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.MULTIPLE_ORDER_CANCELLED.name());
                } else {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.SINGLE_ORDER_CANCELLED.name());
                }
            }

            if (statusString.contains("DELIVER")) {
                if (itemCount > 1) {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.MULTIPLE_ORDER_DELIVERED.name());
                } else {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.SINGLE_ORDER_DELIVERED.name());
                }
            }

            if (statusString.contains("DISPATCH")) {
                if (itemCount > 1) {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.MULTIPLE_ORDER_DISPATCHED.name());
                } else {
                    flowId = Enum.OrderStatusToFlowIdMapper.getString(Enum.OrderStatusToFlowIdMapper.SINGLE_ORDER_DISPATCHED.name());
                }
            }
        }

        return flowId;
    }

    public static String getShortenedName(String name, int length) {
        if (name != null && name.length() > length) {
            return name.substring(0, length) + "...";
        }

        return name;
    }

    private static String getProductName(Item item) {
        if (item != null) {
            Product product = item.getProduct();
            if (product != null) {
                return product.getName();
            }
        }

        return "";
    }

    public static double getTotalOrderAmount(Order order) {
        if (order == null) {
            return 0d;
        }
        double total = 0d;
        if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
            for (Item item : order.getOrderedItems()) {
                if (isItemPurchased(item)) {
                    total += item.getTotalSoldPrice();
                }
            }
        }

        return total;
    }

    public static boolean isMoreThanThirtyMins(Date date) {
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("Asia/Kolkata").toZoneId());

        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, TimeZone.getTimeZone("Asia/Kolkata").toZoneId());
        long mins = TimeUnit.MILLISECONDS.toMinutes(Duration.between(ldt, localNow).toMillis());

        if (mins > 30) {
            return true;
        }

        return false;
    }

    public static String withStarts(String mobile) {
        if (mobile != null) {
            String firstTwo = mobile.substring(0, 2);
            String lastTwo = mobile.substring(mobile.length() - 2, mobile.length());

            return firstTwo + "***" + lastTwo;
        }

        return null;
    }

    public static String checkReturnDate(Date deliveredDate, Integer status, Boolean isAnyOfTheItemIsReturned) {

        if (deliveredDate != null && status.equals(Enum.OrderStatus.DELIVERED.ordinal())) {
            LocalDate date1 = new LocalDate(deliveredDate);
            LocalDate currentDate = new LocalDate(new Date());

            int days = Days.daysBetween(date1, currentDate).getDays();
            isAnyOfTheItemIsReturned = isAnyOfTheItemIsReturned == null ? false : isAnyOfTheItemIsReturned;

            // if a return is placed for any of the items in the order DO NOT allow to
            // return
            if (days <= 7 && isAnyOfTheItemIsReturned == false) {
                return "RETURN_TIME_LIMIT_NOT_EXCEEDED";
            }
        }

        if (status.equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
            return "RETURN_PICKUP_REQUESTED";
        }

        return null;
    }

    public static Date getSevenDaysBeforeDate() {

        Date now = new Date();
        Instant current = now.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault()).minusDays(7);

        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        return output;
    }

    public static Date getThirtyDaysBeforeDate() {

        Date now = new Date();
        Instant current = now.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault()).minusMonths(1);

        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        return output;
    }

    public static List<Integer> getExpiredOrderStatus() {
        List<Integer> list = new ArrayList<>();
        list.add(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal());
        list.add(OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal());
        list.add(OrderStatus.DELIVERED.ordinal());
        list.add(OrderStatus.REFUNDED.ordinal());

        return list;
    }

    /**
     * Supports ONLY 3 levels
     *
     * @param category
     * @return
     */
    public static CategoryDto createChildrenCategory(Category category) {

        if (category == null) {
            return null;
        }

        Integer categoryCount = 0;

        Set<Long> childrenIdsList = new HashSet<>();

        CategoryDto dto = null;

        if (category != null) {

            dto = new CategoryDto(category.getId(), category.getName(), category.getParentCategory());

            childrenIdsList.add(category.getId());

            if (category.getChildrenCategories() != null) {

                List<Category> subCategories = category.getChildrenCategories();
                List<CategoryDto> sub1Dtos = new ArrayList<>();

                if (subCategories != null) {

                    categoryCount += 1;

                    for (Category subCategory : subCategories) {

                        CategoryDto sub1Dto = new CategoryDto(subCategory.getId(), subCategory.getName(),
                                subCategory.getParentCategory());

                        sub1Dtos.add(sub1Dto);

                        childrenIdsList.add(subCategory.getId());

                        List<CategoryDto> sub2Dtos = new ArrayList<>();
                        List<Category> sub2Categories = subCategory.getChildrenCategories();
                        if (sub2Categories != null) {

                            categoryCount += 1;

                            for (Category sub2Category : sub2Categories) {
                                CategoryDto sub2Dto = new CategoryDto(sub2Category.getId(), sub2Category.getName(),
                                        sub2Category.getParentCategory());

                                sub2Dtos.add(sub2Dto);

                                childrenIdsList.add(sub2Category.getId());

                                List<CategoryDto> sub3Dtos = new ArrayList<>();
                                List<Category> sub3Categories = sub2Category.getChildrenCategories();
                                if (sub3Categories != null) {
                                    categoryCount += 1;
                                    for (Category sub3Category : sub3Categories) {

                                        CategoryDto sub3Dto = new CategoryDto(sub3Category.getId(),
                                                sub3Category.getName(), sub3Category.getParentCategory());
                                        sub3Dtos.add(sub3Dto);

                                        childrenIdsList.add(sub3Category.getId());

                                        // sub4 categories start
                                        List<CategoryDto> sub4Dtos = new ArrayList<>();
                                        List<Category> sub4Categories = sub3Category.getChildrenCategories();

                                        if (sub4Categories != null) {
                                            categoryCount += 1;

                                            for (Category sub4Category : sub4Categories) {
                                                CategoryDto sub4Dto = new CategoryDto(sub4Category.getId(),
                                                        sub4Category.getName(), sub4Category.getParentCategory());
                                                sub4Dtos.add(sub4Dto);

                                                childrenIdsList.add(sub4Category.getId());
                                            }

                                            sub3Dto.setChildren(sub4Dtos);
                                        }

                                        // sub4 categories end
                                    }
                                }
                                sub2Dto.setChildren(sub3Dtos);
                            }
                        }
                        sub1Dto.setChildren(sub2Dtos);
                    }
                    dto.setChildren(sub1Dtos);
                }
            }
        }

        dto.setChildrenIds(childrenIdsList);

        return dto;
    }

    public static int calculateDiscount(Double actualPrice, Double price) {

        if (price == null || actualPrice == null) {
            throw new RuntimeException(
                    "Cannot calculate discount for null prices actualPrice = " + actualPrice + " and price = " + price);
        }

        Double discount = 100 - (price / actualPrice) * 100;
        return (int) Math.round(discount);
    }

    public static void backupDatabase(HttpServletRequest request) {
        new Thread(() -> {
            try {
                LOGGER.info("**************Starting shell script from WEB-INF.. **************");
                String[] cmd = new String[]{"/bin/bash",
                        request.getServletContext().getRealPath("/WEB-INF/backupdatabase.sh")};
                Process proc = Runtime.getRuntime().exec(cmd);
                BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                try {
                    proc.waitFor();
                } catch (InterruptedException e) {
                    LOGGER.info("Interrupted" + e.getMessage());
                }
                while (read.ready()) {
                    LOGGER.info("Ready ready " + read.readLine());
                }
                LOGGER.info("**************Shell script execution completed..**************");
            } catch (IOException e) {
                LOGGER.info("IO exception " + e.getMessage());
            }
        }).start();
    }

    private static String getOperatingSystem() {
        return System.getProperty("os.name");
    }

    private static String getMyqlPath() {
        String os = getOperatingSystem();
        String mysqlPath = "/usr/local/mysql/bin";
        if (os != null) {
            if (!os.contains(Constants.MAC_OS)) {
                mysqlPath = "/usr/bin";
            }
        }

        return mysqlPath;
    }

    public static boolean backup() {

        boolean isDumpFileCreatedSuccessfully = false;

        String mysqlPath = getMyqlPath();

        Runtime rt = Runtime.getRuntime();
        Process p;
        try {
            p = rt.exec(mysqlPath + Constants.MYSQL_DUMP_COMMAND);
            InputStream is = p.getInputStream();
            FileOutputStream fos = new FileOutputStream(Constants.MYSQL_DUMP_FILE);
            int ch;
            while ((ch = is.read()) != -1) {
                fos.write(ch);
            }
            fos.close();
            is.close();

            isDumpFileCreatedSuccessfully = true;

        } catch (IOException e) {
            LOGGER.error("Error generating mysql dump ");
            e.printStackTrace();
        }

        LOGGER.info("----SQL backup file generated: mydb_abackup.sql----");

        return isDumpFileCreatedSuccessfully;
    }

    public static String getLast10Digits(String mobile) {

        LOGGER.info("get last 10 digits " + mobile);

        if (mobile != null && mobile.length() == 10) {
            return String.valueOf(mobile);
        } else {
            return String.valueOf(StringUtils.right(mobile, 10));
        }
    }

    public static boolean isWeekEnd() {
        Calendar c1 = Calendar.getInstance();

        if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }

        return true;
    }

    public static boolean isMoreThan24Hours(Date date) {
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("Asia/Kolkata").toZoneId());

        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, TimeZone.getTimeZone("Asia/Kolkata").toZoneId());
        long mins = TimeUnit.MILLISECONDS.toHours(Duration.between(ldt, localNow).toMillis());

        if (mins > 24) {
            return true;
        }

        return false;
    }

    public static Date getIndiaTimeNow() {
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("Asia/Kolkata").toZoneId());
        return Date.from(localNow.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        backup();
    }

    public static String getCityFromIp(String ip) {
        String uri = Application.FIND_CITY_FROM_IP_HOSTNAME + ip + "?" + Application.FIND_IP_API_KEY;
        try {
            return restTemplate.getForObject(uri, String.class);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}