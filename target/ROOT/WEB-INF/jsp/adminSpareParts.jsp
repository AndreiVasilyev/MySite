<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/css/admin.css"/>">
<title>Insert title here</title>
</head>
<body>
	<c:url var="saveNewSparePartUrl" value="/admin/spareparts/save"></c:url>
	<div class="container">
		<spring:form action="${saveNewSparePartUrl}" method="post"
			modelAttribute="newSparePart" id="newSparePartForm"
			class="needs-validation my-4 mx-auto">
			<div class="form-group row ">
				<div class="col-12 text-center text-primary">
					<h3>Добавить новую запчасть</h3>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputSparePartName" class="col-2 col-form-label">Название
					детали:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control"
						id="inputSparePartName" placeholder="Название детали"
						path="sparePartName" required="required" />
					<spring:errors path="sparePartName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputImageLink" class="col-2 col-form-label">Изображение:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputImageLink"
						placeholder="Изображение" path="imageLink" />
					<spring:errors path="imageLink" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputReplacementСost" class="col-2 col-form-label">Стоимость
					замены:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control"
						id="inputReplacementСost" placeholder="стоимость замены"
						path="replacementСost" required="required"></spring:input>
					<spring:errors path="replacementСost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputСompatibleCartridges" class="col-2 col-form-label">Используется
					в картриджах:</label>
				<div class="col-4">
					<spring:select items="${allCartridges}" itemLabel="modelName"
						size="10" class="form-control" id="inputСompatibleCartridges"
						path="compatibleCartridges"></spring:select>
					<spring:errors path="compatibleCartridges" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<label for="inputCompatiblePrinters" class="col-2 col-form-label">Используется
					в принтерах:</label>
				<div class="col-4">
					<spring:select items="${allPrinters}" itemLabel="fullName"
						size="10" class="form-control" id="inputCompatiblePrinters"
						path="compatiblePrinters"></spring:select>
					<spring:errors path="compatiblePrinters" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row justify-content-end">
				<div class="col-auto">
					<input type="submit" class="btn btn-primary"
						id="newSparePartSubbmitButton" value="Сохранить">
				</div>
				<div class="col-auto">
					<input type="button" class="btn btn-secondary"
						id="newSparePartBackButton" value="Назад">
				</div>
			</div>
		</spring:form>
	</div>
	<script src="<c:url value="/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/js/popper.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.js"/>"></script>	
</body>
</html>