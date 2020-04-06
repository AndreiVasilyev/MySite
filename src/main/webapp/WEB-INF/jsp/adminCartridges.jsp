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
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/admin.css">
<title>Insert title here</title>
</head>
<body>
	<c:url var="saveNewCartridgeUrl" value="/admin/cartridges/save"></c:url>
	<div class="container">
		<spring:form action="${saveNewCartridgeUrl}" method="post"
			modelAttribute="newCartridge" id="newCartridgeForm"
			class="needs-validation my-4 mx-auto">
			<div class="form-group row ">
				<div class="col-12 text-center text-primary">
					<h3>Добавить новый картридж</h3>
				</div>
			</div>
			<div class="form-group row ">
				<label for="inputModelName" class="col-2 col-form-label">Модель:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputModelName"
						placeholder="Введите модель" path="modelName" required="required" />
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
				<label for="inputTonerCapacity" class="col-2 col-form-label">Ёмкость:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control"
						id="inputTonerCapacity" placeholder="ёмкость картриджа"
						path="tonerCapacity" />
					<spring:errors path="tonerCapacity" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputResource" class="col-2 col-form-label">Ресурс:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputResource"
						placeholder="ресурс картриджа" path="resource" />
					<spring:errors path="resource" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputRefillCost" class="col-2 col-form-label">Стоимость
					заправки:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputRefillCost"
						placeholder="цена заправки" path="refillCost" required="required"></spring:input>
					<spring:errors path="refillCost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputIsChiped" class="col-2 col-form-label">Наличие
					чипа:</label>
				<div class="col justify-content-start">
					<spring:checkbox class="form-control form-checkbox"
						id="inputIsChiped" path="isChipped"></spring:checkbox>
					<spring:errors path="isChipped" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputChipName" class="col-2 col-form-label">Название
					чипа:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputChipName"
						placeholder="название чипа" path="chipName"></spring:input>
					<spring:errors path="chipName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputChipCost" class="col-2 col-form-label">Стоимость
					чипа:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control" id="inputChipCost"
						placeholder="стоимость чипа" path="chipCost"></spring:input>
					<spring:errors path="chipCost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCompatiblePrinters" class="col-2 col-form-label">Используется
					в:</label>
				<div class="col-4">
					<spring:select items="${allPrinters}" itemLabel="fullName"
						size="10" class="form-control" id="inputCompatiblePrinters"
						path="compatiblePrinters"></spring:select>
					<spring:errors path="compatiblePrinters" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>

				</div>
				<label for="inputNativePrinters" class="col-2 col-form-label">Оригинальный
					для:</label>
				<div class="col-4">
					<spring:select items="${allPrinters}" itemLabel="fullName"
						size="10" class="form-control" id="inputNativePrinters"
						path="nativePrinters"></spring:select>
					<spring:errors path="nativePrinters" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>

				</div>
			</div>
			<div class="form-group row">
				<label for="inputSpareParts" class="col-2 col-form-label">Запчасти:</label>
				<div class="col-10">
					<spring:select items="${allSpareParts}" itemLabel="sparePartName"
						size="5" class="form-control" id="inputSpareParts"
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
	<script src="/js/jquery-3.0.0.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.js"></script>
</body>
</html>