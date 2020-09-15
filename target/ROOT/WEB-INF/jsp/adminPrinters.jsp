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
	<c:url var="saveNewPrinterUrl" value="/admin/printers/save"></c:url>
	<div class="container">
		<spring:form action="${saveNewPrinterUrl}" method="post"
			modelAttribute="newPrinter" id="newPrinterForm"
			class="needs-validation my-4 mx-auto">
			<div class="form-group row ">
				<div class="col-12 text-center text-primary">
					<h3>Добавить новый принтер</h3>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputVendorName" class="col-2 col-form-label">Производитель:</label>
				<div class="col-10">
					<spring:select items="${allVendors}" class="form-control" id="inputVendorName"
						required="required" path="vendorName" />
					<spring:errors path="vendorName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row ">
				<label for="inputModelName" class="col-2 col-form-label">Модель:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputModelName"
						placeholder="Модель" path="modelName" required="required" />
					<spring:errors path="modelName" cssClass="error"
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
				<label for="inputIsFlashable" class="col-2 col-form-label">Необходимость
					прошивки:</label>
				<div class="col-4 justify-content-start">
					<spring:checkbox class="form-control form-checkbox"
						id="inputIsFlashable" path="isReflashable"></spring:checkbox>
					<spring:errors path="isReflashable" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<label for="inputPopularityRating" class="col-2 col-form-label">Рейтинг
					популярности:</label>
				<div class="col-4">
					<spring:input type="text" class="form-control"
						id="inputPopularityRating" placeholder="Рейтинг"
						path="popularityRating" />
					<spring:errors path="popularityRating" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputReflashCost" class="col-2 col-form-label">Стоимость
					прошивки:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control"
						id="inputReflashCost" placeholder="стоимость прошивки"
						path="reflashCost"></spring:input>
					<spring:errors path="reflashCost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputOriginalCartridge" class="col-2 col-form-label">Оригинальный
					картридж:</label>
				<div class="col-10">
					<spring:select items="${allCartridges}" itemLabel="modelName"
						multiple="false" class="form-control" id="inputOriginalCartridge"
						path="originalCartridge"></spring:select>
					<spring:errors path="originalCartridge" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputСompatibleCartridges" class="col-2 col-form-label">Совместимые
					картриджи:</label>
				<div class="col-4">
					<spring:select items="${allCartridges}" itemLabel="modelName"
						size="10" class="form-control" id="inputСompatibleCartridges"
						path="compatibleCartridges"></spring:select>
					<spring:errors path="compatibleCartridges" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<label for="inputSpareParts" class="col-2 col-form-label">Запчасти:</label>
				<div class="col-4">
					<spring:select items="${allSpareParts}" itemLabel="sparePartName"
						size="10" class="form-control" id="inputSpareParts"
						path="spareParts"></spring:select>
					<spring:errors path="spareParts" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>

				</div>
			</div>
			<div class="form-group row justify-content-end">
				<div class="col-auto">
					<input type="submit" class="btn btn-primary"
						id="newPrinterSubbmitButton" value="Сохранить">
				</div>
				<div class="col-auto">
					<input type="button" class="btn btn-secondary"
						id="newPrinterBackButton" value="Назад">
				</div>
			</div>
		</spring:form>
	</div>
	<script src="<c:url value="/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/js/popper.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.js"/>"></script>	
</body>
</html>