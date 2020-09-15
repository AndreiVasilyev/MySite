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
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" href="<c:url value="/css/admin.css"/>">
<title>Insert title here</title>
</head>
<body>
	<c:url var="saveNewCartridgeUrl" value="/admin/cartridges/save"></c:url>
	<c:url var="mainAdminPageUrl" value="/admin"></c:url>
	<div class="container">
		<spring:form action="${saveNewCartridgeUrl}" method="post"
			modelAttribute="newCartridge" id="newCartridgeForm"
			class="needs-validation my-4 mx-auto">
			<div class="form-group row ">
				<div class="col-12 text-center text-primary">
					<h3>Добавить/изменить картридж</h3>
				</div>
			</div>			
			<div class="form-group row">
				<label for="inputId" class="col-2 col-form-label">ID:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputId" path="id" value="${newCartridge.id}" readonly="true" />
				</div>
			</div>	
			<div class="form-group row ">
				<label for="inputModelName" class="col-2 col-form-label">Модель:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputModelName" placeholder="Модель" path="modelName"
						required="required" value="${newCartridge.modelName}" />
					<spring:errors path="modelName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputImageLink" class="col-2 col-form-label">Изображение:</label>
				<div class="col-1" id="imageContainer">
				<c:if test="${not empty newCartridge.id}">
					<img alt="${newCartridge.imageLink}" src="/images/cartridges/${newCartridge.imageLink}" height="50" width="50">
				</c:if>	
				</div>
				<div class="col">
					<spring:input type="text" class="form-control border-success"
						id="inputImageLink" placeholder="Изображение" path="imageLink"
						value="${newCartridge.imageLink}" />
					<spring:errors path="imageLink" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<div class="col-auto">
					<label for="attachImageLink"
						class="form-control border-success attach-label text-info font-weight-bold text-uppercase">
						<i class="fa fa-paperclip"></i> Файл <input type="file" class=""
						id="attachImageLink" name="imageFile" accept=".jpg"/>
					</label>
				</div>
			</div>	
			<div class="form-group row">
				<label for="inputTonerCapacity" class="col-2 col-form-label">Ёмкость:</label>
				<div class="col-10"> 
					<spring:input type="text" class="form-control border-success" id="inputTonerCapacity" placeholder="Ёмкость"
						path="tonerCapacity" value="${newCartridge.tonerCapacity}" />
					<spring:errors path="tonerCapacity" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputResource" class="col-2 col-form-label">Ресурс:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success" id="inputResource"
						placeholder="Ресурс" path="resource" value="${newCartridge.resource}" />
					<spring:errors path="resource" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputRefillCost" class="col-2 col-form-label">Стоимость
					заправки:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success" id="inputRefillCost"
						placeholder="цена заправки" path="refillCost" required="required" value="${newCartridge.refillCost}" />
					<spring:errors path="refillCost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputIsChiped" class="col-2 col-form-label">Наличие
					чипа:</label>
				<div class="col-4 justify-content-start">
					<c:if test="${newCartridge.isChipped}">
						<spring:checkbox class="form-control form-checkbox"
							id="inputIsChiped" path="isChipped" checked="true"></spring:checkbox>
					</c:if>
					<c:if test="${not newCartridge.isChipped}">
						<spring:checkbox class="form-control form-checkbox"
							id="inputIsChiped" path="isChipped"></spring:checkbox>
					</c:if>
					<spring:errors path="isChipped" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<label for="inputChipName" class="col-2 col-form-label">Название
					чипа:</label>
				<div class="col-4">
					<spring:input type="text" class="form-control border-success"
						id="inputChipName" placeholder="Название"
						path="chipName" value="${newCartridge.chipName}" />
					<spring:errors path="chipName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputChipCost" class="col-2 col-form-label">Стоимость
					чипа:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success" id="inputChipCost"
						placeholder="стоимость чипа" path="chipCost" value="${newCartridge.chipName}" />
					<spring:errors path="chipCost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCompatiblePrinters" class="col-2 col-form-label">Используется в:</label>
				<div class="col-4">
					<spring:select multiple="true" size="10"
						class="form-control text-center" id="allCompatiblePrinters"
						path="">
						<spring:options items="${allPrinters}" itemLabel="fullName"
							itemValue="id" />
					</spring:select>
				</div>
				<div class="col-auto p-0 align-self-center">
					<a class="btn btn-success text-white d-block mb-2"><i
						class="fa fa-angle-double-right"></i></a> <a
						class="btn btn-danger text-white d-block"><i
						class="fa fa-angle-double-left"></i></a>
				</div>
				<div class="col">
					<spring:select multiple="true" size="10"
						class="form-control text-center border-success"
						id="inputCompatiblePrinters" path="compatiblePrintersId">
						<c:if test="${not empty newCartridge.id}">
							<spring:options items="${newCartridge.compatiblePrinters}"
								itemLabel="fullName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="compatiblePrintersId" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputNativePrinters" class="col-2 col-form-label">Оригинальный для:</label>
				<div class="col-4">
					<spring:select multiple="true" size="10"
						class="form-control text-center" id="allNativePrinters"
						path="">
						<spring:options items="${allPrinters}" itemLabel="fullName"
							itemValue="id" />
					</spring:select>
				</div>
				<div class="col-auto p-0 align-self-center">
					<a class="btn btn-success text-white d-block mb-2"><i
						class="fa fa-angle-double-right"></i></a> <a
						class="btn btn-danger text-white d-block"><i
						class="fa fa-angle-double-left"></i></a>
				</div>
				<div class="col">
					<spring:select multiple="true" size="10"
						class="form-control text-center border-success"
						id="inputNativePrinters" path="nativePrintersId">
						<c:if test="${not empty newCartridge.id}">
							<spring:options items="${newCartridge.nativePrinters}"
								itemLabel="fullName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="nativePrintersId" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>	
			<div class="form-group row">
				<label for="inputSpareParts" class="col-2 col-form-label">Запчасти:</label>
				<div class="col-4">
					<spring:select multiple="true" size="10"
						class="form-control text-center" id="allSpareParts"
						path="">
						<spring:options items="${allSpareParts}" itemLabel="sparePartName"
							itemValue="id" />
					</spring:select>
				</div>
				<div class="col-auto p-0 align-self-center">
					<a class="btn btn-success text-white d-block mb-2"><i
						class="fa fa-angle-double-right"></i></a> <a
						class="btn btn-danger text-white d-block"><i
						class="fa fa-angle-double-left"></i></a>
				</div>
				<div class="col">
					<spring:select multiple="true" size="10"
						class="form-control text-center border-success"
						id="inputSpareParts" path="sparePartsId">
						<c:if test="${not empty newCartridge.id}">
							<spring:options items="${newCartridge.spareParts}"
								itemLabel="sparePartName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="sparePartsId" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>	
			<div class="form-group row justify-content-end">
				<div class="col-auto">
					<input type="submit" class="btn btn-primary"
						id="newCartridgeSubbmitButton" value="Сохранить">
				</div>
				<div class="col-auto">
					<a class="btn btn-secondary" href="${mainAdminPageUrl}">Назад</a>
				</div>
			</div>
		</spring:form>
	</div>
	<script src="<c:url value="/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/js/popper.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.js"/>"></script>	
	<script src="<c:url value="/js/admin-cartridge.js"/>"></script>
	<script src="<c:url value="/js/admin-add-common.js"/>"></script>
	<script src="<c:url value="/js/admin-ajax.js"/>"></script>
</body>
</html>