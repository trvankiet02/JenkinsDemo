<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/taglib.jsp"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Hello, world!</title>
</head>
<body>

	<section class="row">

		<div class="col mt-4">
			<form action=<c:url value = "/admin/categories/saveOrUpdate"/>
				method="POST">
				<div class="card">
					<div class="card-header">
						<h2>${category.isEdit ? 'Edit Category' : 'Add New Category'}</h2>
					</div>

				</div>
				<div class="card-body">

					<div class="mb-3">
						<label for="categoryId" class="form-lablel">Category ID:</label> <input
							type="hidden" value="${category.isEdit}"> <input
							type="text" class="form-control" readonly="readonly"
							name="categoryId" value="${category.categoryId}" id="categoryId"
							aria-describedby="categoryIdid" placeholder="Category Id" />
					</div>

					<div class="mb-3">
						<label for="categoryname" class="form-lablel">Category
							Name:</label> <input type="text" value="${category.categoryname}"
							id="categoryname" name="categoryname"
							aria-describedby="categorynameid" placeholder="Category Name">
					</div>

					<div class="mb-3">
						<label for="categorycode" class="form-lablel">Category
							Code:</label> <input type="text" value="${category.categorycode}"
							id="categorycode" name="categorycode">

					</div>

					<div class="mb-3">
						<label for="Satus" class="form-lablel">Status:</label> 
						<select class="form-select" name="status" aria-describedby="categorycodeid" id="status">

							<option ${category.status == true ? 'selected':'' } value="true">Còn</option>
							<option ${category.status == false ? 'selected':'' } value="false">Hết</option>
						</select>

					</div>

					<div class="card-footer text-muted">
						<a href=<c:url value="/admin/categories/add"/>
							class="btn btn-secondary"><i class="fas fa-new"></i>New</a> <a
							href=<c:url value="/admin/categories"/> class="btn btn-success"><i
							class="fas bars"></i>List Category</a>
						<button class="btn btn-primary" type="submit">
							<i class="fas fa-save"></i>
							<c:if test="${category.isEdit}">
								<span>Update</span>
							</c:if>

							<c:if test="${!category.isEdit}">
								<span>Save</span>
							</c:if>

						</button>
					</div>
			</form>

		</div>

	</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>