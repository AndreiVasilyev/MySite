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
	<c:url var="saveNewSparePartUrl" value="/admin/spareparts/save"></c:url>
	<c:url var="mainAdminPageUrl" value="/admin"></c:url>
	<div class="container">
		<spring:form action="${saveNewSparePartUrl}" method="post"
			modelAttribute="newSparePart" id="newSparePartForm"
			class="needs-validation my-4 mx-auto">
			<div class="form-group row ">
				<div class="col-12 text-center text-primary">
					<h3>Добавить/изменить запчасть</h3>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputId" class="col-2 col-form-label">ID:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputId" path="id" value="${newSparePart.id}" readonly="true" />
				</div>
			</div>
			<div class="form-group row">
				<label for="inputSparePartName" class="col-2 col-form-label">Название
					детали:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputSparePartName" placeholder="Название детали"
						path="sparePartName" required="required"
						value="${newSparePart.sparePartName}" />
					<spring:errors path="sparePartName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputImageLink" class="col-2 col-form-label">Изображение:</label>
				<div class="col-1" id="imageContainer">
					<c:if test="${not empty newCartridge.id}">
						<img alt="${newSparePart.imageLink}"
							src="/images/cartridges/${newSparePart.imageLink}" height="50"
							width="50">
					</c:if>
				</div>
				<div class="col">
					<spring:input type="text" class="form-control border-success"
						id="inputImageLink" placeholder="Изображение" path="imageLink"
						value="${newSparePart.imageLink}" />
					<spring:errors path="imageLink" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<div class="col-auto">
					<label for="attachImageLink"
						class="form-control border-success attach-label text-info font-weight-bold text-uppercase">
						<i class="fa fa-paperclip"></i> Файл <input type="file" class=""
						id="attachImageLink" name="imageFile" accept=".jpg" />
					</label>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputReplacementСost" class="col-2 col-form-label">Стоимость
					замены:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputReplacementСost" placeholder="стоимость замены"
						path="replacementСost" required="required"
						value="${newSparePart.replacementСost}"></spring:input>
					<spring:errors path="replacementСost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputСompatibleCartridges" class="col-2 col-form-label">Используется
					в картриджах:</label>
				<div class="col-4">
					<spring:select multiple="true" size="10"
						class="form-control text-center" id="allСompatibleCartridges"
						path="">
						<spring:options items="${allCartridges}" itemLabel="modelName"
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
						id="inputСompatibleCartridges" path="compatibleCartridgesId">
						<c:if test="${not empty newSparePart.id}">
							<spring:options items="${newSparePart.compatibleCartridges}"
								itemLabel="modelName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="compatibleCartridgesId" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCompatiblePrinters" class="col-2 col-form-label">Используется
					в принтерах:</label>
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
						<c:if test="${not empty newSparePart.id}">
							<spring:options items="${newSparePart.compatiblePrinters}"
								itemLabel="fullName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="compatiblePrintersId" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row justify-content-end">
				<div class="col-auto">
					<input type="submit" class="btn btn-primary"
						id="newSparePartSubbmitButton" value="Сохранить">
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
	<script src="<c:url value="/js/admin-part.js"/>"></script>
	<script src="<c:url value="/js/admin-add-common.js"/>"></script>
	<script src="<c:url value="/js/admin-ajax.js"/>"></script>
</body>
</html>