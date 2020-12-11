
<%
	/*
	   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
	*/
%>
<%@ page import="java.io.*,java.util.*,com.ccavenue.security.*"%>
<html>
<head>
<title>Sub-merchant checkout page</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<%
		String accessCode = "AVVQ88GK68AN28QVNA"; //Put in the Access Code in quotes provided by CCAVENUES.
		String workingKey = "E22D993DF43A10D29C09AC0A9631EAAA"; //Put in the 32 Bit Working Key provided by CCAVENUES.  
		Map<String, String> map = (Map<String, String>) request.getAttribute("paymentParameters");
		String ccaRequest = "", pname = "", pvalue = "";

		for (Map.Entry<String, String> entry : map.entrySet()) {
			pname = "" + entry.getKey();
			pvalue = entry.getValue();
			ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
		}

		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		//out.println("<!--" + ccaRequest + "-->");
		String encRequest = aesUtil.encrypt(ccaRequest);
	%>

	<!-- https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction -->

	<form id="nonseamless" method="post" name="redirect"
		action="https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction" />
	<input type="hidden" id="encRequest" name="encRequest"
		value="<%=encRequest%>">
	<input type="hidden" name="access_code" id="access_code"
		value="<%=accessCode%>">
	<input type="hidden" name="billing_name" value="velmani" />
	<input type="hidden" name="billing_address" value="3/177 vadvalli" />
	<input type="hidden" name="billing_country" value="India" />
	<input type="hidden" name="billing_tel" value="9901411006" />
	<input type="hidden" name="billing_email" value="amirtharaj@live.com" />
	<input type="hidden" name="billing_state" value="Karnataka" />
	<input type="hidden" name="billing_city" value="Bangalore" />
	<input type="hidden" name="billing_zip" value="560047" />
	<input type="hidden" name="merchant_param1" value="custom muraleethara" />

	<script language='javascript'>
		document.redirect.submit();
	</script>
	<!-- <script>
	$(document).ready(function() {
		alert(" ccavRequestHandler ");
	});
</script> -->
	</form>

</body>
</html>
