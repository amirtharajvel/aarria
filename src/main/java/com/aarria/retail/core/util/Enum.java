package com.aarria.retail.core.util;

public class Enum {

	public enum Msg91SMSRoute {
		PROMOTIONAL(1), TRANSACTIONAL(4);

		private int s;

		Msg91SMSRoute(int s) {
			this.s = s;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (Msg91SMSRoute i : Msg91SMSRoute.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum ProductDescriptionType {
		PRODUCT_DESC(0), SHIP_AND_RETURNS(1);

		private int s;

		ProductDescriptionType(int s) {
			this.s = s;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (ProductDescriptionType i : ProductDescriptionType.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum Country {
		INDIA(1);

		int c;

		Country(int c) {
			this.c = c;
		}

		public static String getString(int s) {
			for (Country i : Country.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}

		public int getValue() {
			return c;
		}
	}

	public enum OrderStatusToFlowIdMapper {
		OTP("60f99db51d2e025d4a5a2c89"),
		SINGLE_ORDER_CONFIRMED("60efa52810c60c16bb0a5575"),
		MULTIPLE_ORDER_CONFIRMED("60f7132bb7ecc722cc410929"),
		SINGLE_ORDER_CANCELLED("60f71448b7ecc722cc41092c"),
		MULTIPLE_ORDER_CANCELLED("60f712c19cac38232b0cd5a6"),
		SINGLE_ORDER_DISPATCHED("60f7152ffa6a9f0b1471bd72"),
		MULTIPLE_ORDER_DISPATCHED("60f713828e6a8466d2521266"),
		SINGLE_ORDER_DELIVERED("60f713dd8e6a8466d2521267"),
		MULTIPLE_ORDER_DELIVERED("60f714d390d4cc111948bf79");

		private String s;
		OrderStatusToFlowIdMapper(String s) {
			this.s = s;
		}

		public String getValue() {
			return s;
		}

		public static String getString(String s) {
			for (OrderStatusToFlowIdMapper i : OrderStatusToFlowIdMapper.values()) {
				if (i.getValue().equals(s)) {
					return i.name();
				}
			}

			return null;
		}
	}


	public enum OrderStatus {
		OPEN(0), BEING_PACKED(1), DISPATCHED(2), DELIVERED(3), CANCELLED_WITHIN_30_MINS(4), CANCELLED_WHILE_PACKAGING(
				5), RETURN_PICKUP_REQUESTED(6), RETURNED(
						7), REFUND_INITIATED(8), REFUNDED(9), NON_COD_PAYMENT_NOT_DONE(10), COD_NOT_CONFIRMED(11);

		private int s;

		OrderStatus(int s) {
			this.s = s;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (OrderStatus i : OrderStatus.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum ItemStatus {
		OPEN(0), CANCELLED_WITHIN_30_MINS(1), CANCELLED_WHILE_PACKAGING(2), RETURN_PICKUP_REQUESTED(3), RETURNED(
				4), REFUND_INITIATED(5), REFUNDED(6);

		private int s;

		ItemStatus(int s) {
			this.s = s;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (ItemStatus i : ItemStatus.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum PriceBreakup {
		GST, SHIP, PAY, PROFIT, SHIPGST, PACK
	}

	public enum ReturnCashMethod {
		WALLET(0), BANK_ACCOUNT(1);

		private int s;

		ReturnCashMethod(int a) {
			s = a;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (ReturnCashMethod i : ReturnCashMethod.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum SignupType {
		CHECKOUT(0), MANUAL(1);

		private int s;

		SignupType(int s) {
			this.s = s;
		}

		public int getValue() {
			return s;
		}
	}

	// If you add anything new modify in ProductDetailsController
	public enum Refiners {
		COLOR, SIZE
	}

	public enum ImageType {
		THUMBNAIL("thumbnail"), PREVIEW("preview"), ORIGINAL("original"), SMALL("small");

		private String s;

		ImageType(String a) {
			s = a;
		}

		public String getValue() {
			return s;
		}

		public static String getString(String s) {
			for (PaymentMethod i : PaymentMethod.values()) {
				if (i.equals(s)) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum CheckoutType {
		BUYNOW, ADDTOCART
	}

	public enum Activity {
		ORDER_IS_GREATER_THAN_STOCK, SIZE_JUST_OLD_OUT, PRODUCT_JUST_SOLD_OUT, OLD_PRODUCT
	}

	public enum Shipper {
		INDIA_POST, FEDEX
	}

	public enum PaymentMethod {
		COD(0), NON_COD(1);

		private int s;

		PaymentMethod(int a) {
			s = a;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (PaymentMethod i : PaymentMethod.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum PlaceOrderFailReason {
		PRODUCT_COUNT_MISMATCH(0), TECHNICAL_FAILURE(1);

		private int s;

		PlaceOrderFailReason(int a) {
			s = a;
		}

		public int getValue() {
			return s;
		}

		public static String getString(int s) {
			for (PlaceOrderFailReason i : PlaceOrderFailReason.values()) {
				if (i.getValue() == s) {
					return i.name();
				}
			}

			return null;
		}
	}

	public enum OTPAction {
		LOGIN_ON_CHECKOUT, REGISTER, FORGOT_PASSWORD, SEND_EMAIL_OR_MOBILE
	}
}
