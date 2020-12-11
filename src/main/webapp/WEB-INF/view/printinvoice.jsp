<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="utf-8">
<title>Online Shopping: Shop Online for Clothing for Women, Men
	and Kids. Buy hand picked Export Quality Sarees, Kurtis, Choli's,
	Anarkali, Tops, Lahankas, Party Wear, Kids Wear at low price.
	Fallsbuy.com</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="/resources/assets/invoice/css/invoice.css" rel="stylesheet">
<style type="text/css">
html, body {
	font-family: "Source Sans Pro", sans-serif !important;
}

table {
	border-collapse: collapse;
	border: 1px solid gray;
	width: 600px;
	font-family: Tahoma, Arial, sans-serif;
}

tr {
	font-size: 12px;
}

td {
	padding-right: 9px;
	padding-left: 9px;
}

.ttable {
	width: 75%;
}

.no-border-table {
	border: none;
}
</style>
<script type="text/javascript">
	
</script>
<title></title>
</head>
<body>




	<table align="center" class="ttable">
		<tbody>
			<tr>
				<th colspan="2"><span style="font-size: 15px;">Tax
						Invoice</span></th>
			</tr>
			<tr style="border-top-style: none">
				<td align="left"><span
					style="font-size: 20px; font-weight: bold;">Ship To: <br />
				</span>${invoice.shippingAddress.name },<br />

					<div style="width: 40%; word-wrap: break-word;">${invoice.shippingAddress.address },</div>
					<c:if test="${invoice.shippingAddress.landmark ne '' }">Landmark: ${invoice.shippingAddress.landmark },<br />
					</c:if>Pincode: ${invoice.shippingAddress.pincode }, <br /> Mobile:
					${invoice.shippingAddress.mobile }</td>

				<td style="vertical-align: top" align="right"><c:if
						test="${invoice.isCashOnDelivery == true}">
						 Mode: <span style="font-size: 20px; font-weight: bold;">
							Cash On Delivery (COD) </span>
						<br />
					</c:if> Invoice Number: ${invoice.invoiceNo }<br> Invoice Date:
					${invoice.invoiceDate }<br> Receipt Voucher :
					FB-${invoice.receiptVoucher}</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr style="border-top-style: none">
				<td align="left"><span
					style="font-size: 20px; font-weight: bold;">Bill To: <br />
				</span>${invoice.shippingAddress.name },<br />

					<div style="width: 40%; word-wrap: break-word;">${invoice.shippingAddress.address },</div>
					<c:if test="${invoice.shippingAddress.landmark ne '' }">${invoice.shippingAddress.landmark },<br />
					</c:if>${invoice.shippingAddress.pincode }, <br />
					${invoice.shippingAddress.mobile }</td>

				<td style="vertical-align: top" align="right"><span
					style="font-weight: bold;">Sold By:</span> <br />Fallsbuy
					ECommerce Private Limited</strong> <br /> #304, 57/2,
					, <br>14th Cross, Vivek Nagar [PO], <br />Ejipura,
					Bangalore - 560047 <br /> PAN #: AACCF8077F <br> <!-- CIN #:
					U74999MH2011PTC218349 <br>  -->GSTIN #: 33AACCF8077F1ZN</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><strong></td>
			</tr>
		</tbody>
	</table>






	<table style="border-top-style: none" align="center" class="ttable">
		<tr>
			<th class="thcolumn">Sr No.</th>
			<th class="thcolumn">Description of Product</th>
			<th class="thcolumn">HSN / SAC Code</th>
			<th class="thcolumn">Qty</th>
			<th class="thcolumn">Unit Price</th>
			<th class="thcolumn">Total Base Price</th>
			<th class="thcolumn">Discount</th>
			<th class="thcolumn">Taxable Value</th>
			<th class="thcolumn">IGST Rate</th>
			<th class="thcolumn">IGST Amt</th>
			<th class="thcolumn">Net Amount</th>
		</tr>
		<c:forEach items="${invoice.items}" var="item">
			<tr>
				<td class="trow">${item.serialNo}</td>
				<td class="trow">${item.descriptionOfSupplies}</td>
				<td class="trow">${item.hanCode}</td>
				<td class="trow">${item.quantity}</td>
				<td class="trow">${item.unitPrice}</td>
				<td class="trow">${item.totalBasePrice}</td>
				<td class="trow">${ item.discount}</td>
				<td class="trow">${item.taxableValue}</td>
				<td class="trow">${item.igstRate}</td>
				<td class="trow">${item.igstAmount}</td>
				<td class="trow">${item.netAmount}</td>
			</tr>
		</c:forEach>
		<tr>
			<td class="nettd" colspan="10">Total Invoice Amount</td>
			<td align="right" class="nettd">
				&#8377;${invoice.totalInvoiceAmount}</td>
		</tr>
		<tr>
			<td colspan="11" align="left" style="font-weight: bold">For
				Fallsbuy ECommerce India Pvt Limited</td>
		</tr>
		<tr>
			<td colspan="11" align="left" style="font-weight: bold"><img
				alt="Authorised Signatory"
				src="resources/assets/images/signature.jpg"></td>
		</tr>
		<tr>
			<td colspan="11" align="left" style="font-weight: bold">Name :
				Amirtharaj V</td>
		</tr>
		<tr>
			<td colspan="11" align="left" style="font-weight: bold">

				Designation : Authorised Signatory</td>
		</tr>
		</tbody>
	</table>



</body>
</html>