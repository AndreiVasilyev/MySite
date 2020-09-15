<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" href="<c:url value="/css/admin.css"/>">
<title>Main administration page</title>
</head>
<body>
	<div class="modal fade" id="modalWindow" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalTitle">Удаление записи из
						базы данных</h5>
					<button type="button" class="close modal-button"
						data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p id="modalMessage">
						Будет удалена запись с id=<span id="idValue"></span>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary modal-button"
						data-dismiss="modal">Отмена</button>
					<button type="button" class="btn btn-primary modal-button"
						id="removeModalButton">Удалить</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<c:url var="addNewPrinterUrl" value="/admin/printer/edit"></c:url>
		<c:url var="addNewCartridgeUrl" value="/admin/cartridge/edit"></c:url>
		<c:url var="addNewPartUrl" value="/admin/part/edit"></c:url>
		<div class="row justify-content-center p-1 text-white bg-primary">
			<div class="col text-center">
				<h2 class="">Страница управления сайтом qhp.by</h2>
			</div>
		</div>
		<div class="row mt-2">
			<div class="col text-center">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#printerstab">Printers</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#cartridgestab">Cartridges</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#sparepartstab">Spare parts</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="pricestab">Цены</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#commentstab">Отзывы</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#orderstab">Заказы</a></li>
				</ul>
				<div class="d-none current-tab">${tab}</div>
				<div class="tab-content">
					<div class="tab-pane fade show active" id="printerstab" data-tabsource="printer">
						<div class="btn-toolbar">
							<div class="btn-group my-2 ml-auto">
								<button type="button" class="btn btn-outline-warning reset-filter-button">Сбросить фильтр</button>
								<a class="btn btn-info font-weight-bold"
									href="${addNewPrinterUrl}"> <i class="fa fa-plus m-1"></i>new
								</a>
							</div>
						</div>
						<div class="table-container p-0">
							<table class="table table-hover table-sm">
								<thead>
									<tr class="bg-success">
										<th scope="col" class="bg-success text-white"><span>id</span><a
											class="ml-1 sort-link" data-order="asc"
											data-name="id"><i class="fa fa-sort-asc"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>vendorName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="vendorName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>modelName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="modelName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>imageLink</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="imageLink"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>rating</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="popularityRating"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>isReflashable</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="isReflashable"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>reflashCost</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="reflashCost"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>originCartridge</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="originalCartridge"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"></th>
									</tr>
								</thead>
								<tbody id="printersTableBody">
									<tr class="bg-white">
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white px-0">
											<div
												class="input-group input-group-sm chk-filter-group justify-content-center">
												<label class="mr-1"><input type="checkbox"
													class="filter-input d-block mx-auto"
													disabled="disabled" checked>true</label> <label
													class="ml-1"><input type="checkbox"
													class="filter-input d-block mx-auto"
													disabled="disabled" checked>false</label>

											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text" class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white filter-input"></td>
									</tr>									
								</tbody>
							</table>
						</div>
						<div class="alert alert-secondary mt-2 font-weight-bold p-1">
							Найдено всего: <span class="searchResult">${printersCount}</span>
						</div>
					</div>
					<div class="tab-pane fade" id="cartridgestab" data-tabsource="cartridge">
						<div class="btn-toolbar">
							<div class="btn-group my-2 ml-auto">
								<button type="button" class="btn btn-outline-warning reset-filter-button">Сбросить фильтр</button>
								<a class="btn btn-info font-weight-bold"
									href="${addNewCartridgeUrl}"> <i class="fa fa-plus m-1"></i>new
								</a>
							</div>
						</div>
						<div class="table-container p-0">
							<table class="table table-hover table-sm">
								<thead>
									<tr class="bg-success">
										<th scope="col" class="bg-success text-white"><span>id</span><a
											class="ml-1 sort-link" data-order="asc"
											data-name="id"><i class="fa fa-sort-asc"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>modelName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="modelName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>imageLink</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="imageLink"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>refillCost</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="refillCost"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>isChipped</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="isChipped"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>chipCost</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="chipCost"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>resource</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="resource"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>tonerCapacity</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="tonerCapacity"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"></th>
									</tr>
								</thead>
								<tbody id="cartridgesTableBody">
									<tr class="bg-white">
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white px-0">
											<div
												class="input-group input-group-sm chk-filter-group justify-content-center">
												<label class="mr-1"><input type="checkbox"
													class="filter-input d-block mx-auto"
													disabled="disabled" checked>true</label> <label
													class="ml-1"><input type="checkbox"
													class="filter-input d-block mx-auto"
													disabled="disabled" checked>false</label>

											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white filter-input"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="alert alert-secondary mt-2 font-weight-bold p-1">
							Найдено всего: <span class="searchResult">${cartridgesCount}</span>
						</div>
					</div>
					<div class="tab-pane fade" id="sparepartstab" data-tabsource="part">
						<div class="btn-toolbar">
							<div class="btn-group my-2 ml-auto">
								<button type="button" class="btn btn-outline-warning reset-filter-button">Сбросить фильтр</button>
								<a class="btn btn-info font-weight-bold"
									href="${addNewPartUrl}"> <i class="fa fa-plus m-1"></i>new
								</a>
							</div>
						</div>
						<div class="table-container p-0">
							<table class="table table-hover table-sm">
								<thead>
									<tr class="bg-success">
										<th scope="col" class="bg-success text-white"><span>id</span><a
											class="ml-1 sort-link" data-order="asc"
											data-name="id"><i class="fa fa-sort-asc"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>sparePartName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="sparePartName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>imageLink</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="imageLink"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>replacementСost</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="replacementСost"><i class="fa fa-sort"></i></a></th>										
										<th scope="col" class="bg-success text-white"></th>
									</tr>
								</thead>
								<tbody id="partsTableBody">
									<tr class="bg-white">
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>										
										<td class="stick-filter bg-white filter-input"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="alert alert-secondary mt-2 font-weight-bold p-1">
							Найдено всего: <span class="searchResult">${partsCount}</span>
						</div>
					</div>	
					<div class="tab-pane fade" id="commentstab" data-tabsource="comment">
						<div class="btn-toolbar">
							<div class="btn-group my-2 ml-auto">
								<button type="button" class="btn btn-outline-warning reset-filter-button">Сбросить фильтр</button>								
							</div>
						</div>
						<div class="table-container p-0">
							<table class="table table-hover table-sm">
								<thead>
									<tr class="bg-success">
										<th scope="col" class="bg-success text-white"><span>id</span><a
											class="ml-1 sort-link" data-order="asc"
											data-name="id"><i class="fa fa-sort-asc"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>authorName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="authorName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>dateMessage</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="dateMessage"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>emailAddress</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="emailAddress"><i class="fa fa-sort"></i></a></th>	
										<th scope="col" class="bg-success text-white"><span>phoneNumber</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="phoneNumber"><i class="fa fa-sort"></i></a></th>		
										<th scope="col" class="bg-success text-white"><span>companyName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="companyName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>isPublic</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="isPublic"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>message</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="message"><i class="fa fa-sort"></i></a></th>
									</tr>
								</thead>
								<tbody id="commentsTableBody">
									<tr class="bg-white">
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>	
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>	
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>															
										<td class="stick-filter bg-white px-0">
											<div
												class="input-group input-group-sm chk-filter-group justify-content-center">
												<label class="mr-1"><input type="checkbox"
													class="filter-input d-block mx-auto"
													disabled="disabled" checked>true</label> <label
													class="ml-1"><input type="checkbox"
													class="filter-input d-block mx-auto"
													disabled="disabled" checked>false</label>
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>				
									</tr>
								</tbody>
							</table>
						</div>
						<div class="alert alert-secondary mt-2 font-weight-bold p-1">
							Найдено всего: <span class="searchResult">${commentsCount}</span>
						</div>
					</div>	
					
					<div class="tab-pane fade" id="orderstab" data-tabsource="order">
						<div class="btn-toolbar">
							<div class="btn-group my-2 ml-auto">
								<button type="button" class="btn btn-outline-warning reset-filter-button">Сбросить фильтр</button>								
							</div>
						</div>
						<div class="table-container p-0">
							<table class="table table-hover table-sm">
								<thead>
									<tr class="bg-success">
										<th scope="col" class="bg-success text-white"><span>id</span><a
											class="ml-1 sort-link" data-order="asc"
											data-name="id"><i class="fa fa-sort-asc"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>customerName</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="customerName"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>phoneNumber</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="phoneNumber"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>dateOrder</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="dateOrder"><i class="fa fa-sort"></i></a></th>	
										<th scope="col" class="bg-success text-white"><span>emailAddress</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="emailAddress"><i class="fa fa-sort"></i></a></th>		
										<th scope="col" class="bg-success text-white"><span>customerAddress</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="customerAddress"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>additionalInfo</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="additionalInfo"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>deviceModel</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="deviceModel"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>orderCondition</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="orderCondition"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>orderSource</span><a
											class="ml-1 sort-link" data-order="no"
											data-name="orderSource"><i class="fa fa-sort"></i></a></th>
										<th scope="col" class="bg-success text-white"><span>orderAdditions</span></th>
									</tr>
								</thead>
								<tbody id="ordersTableBody">
									<tr class="bg-white">
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>	
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>	
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>															
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<input type="text"
													class="form-control filter-input"
													disabled="disabled">
											</div>
										</td>	
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">												
												<select class="form-control filter-input">
													<option value="" label=""></option>
													<c:forEach var="condition" items="${conditions}">
														<option value="${condition}" label="${condition}"></option>
													</c:forEach>
												</select>
											</div>
										</td>	
										<td class="stick-filter bg-white">
											<div class="input-group input-group-sm">
												<select class="form-control filter-input">
													<option value="" label=""></option>
													<c:forEach var="ordersource" items="${ordersources}">
														<option value="${ordersource}" label="${ordersource}"></option>
													</c:forEach>
												</select>
											</div>
										</td>													
									</tr>
								</tbody>
							</table>
						</div>
						<div class="alert alert-secondary mt-2 font-weight-bold p-1">
							Найдено всего: <span class="searchResult">${ordersCount}</span>
						</div>
					</div>				
					
					
									
				</div>
			</div>
		</div>
	</div>

	<script src="<c:url value="/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/js/popper.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/js/admin-ajax.js"/>"></script>
	<script src="<c:url value="/js/admin.js"/>"></script>
</body>
</html>