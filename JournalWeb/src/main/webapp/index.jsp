<%@ page import="com.journal.ListJournal"%>
<%@ page import="com.journal.Record"%>
<%@ page import="java.util.List"%>
<html>
<body>
	<h2>Journal</h2>
	<%!ListJournal journal = ListJournal.getInstance();
	List<Record> records = journal.getRecords();%>

	<table border="1">
		<tr>
			<th>Record ID</th>
		</tr>

		<%
			for (Record r : records) {
				out.println("<tr><td>");
				out.println(r.getId());
				out.println("</td></tr>");
			}
		%>



	</table>
</body>
</html>
