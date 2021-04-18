<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
</script>
<style type="text/css">

.modal {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
.modal_overlay {
	background-color: rgba(0,0,0, 0.6);
	width: 100%;
	height: 100%;
	position: absolute;
}
.modal_content {
	background-color: white;
	padding: 50px 50px;
	text-align: center;
	position: relative;
	border-radius: 10px;
	box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
}
.hidden {
	display: none;
}
</style>
</head>
<body>
<button id="open">open modal</button>
<div class="modal hidden">
	<div class="modal_overlay"></div>
	<div class="modal_content">
		<h1> hi i am mo dal</h1>
		<button id="closeBtn">X</button>
	</div>
</div>
<script type="text/javascript">
	const openButton = document.getElementById("open");
	const modal = document.querySelector(".modal");
	const overlay = modal.querySelector(".modal_overlay");
	const closeBtn =  modal.querySelector("#closeBtn");
	
	const openModal = () => {
		modal.classList.remove("hidden")
	}
	const closeModal = () => {
		modal.classList.add("hidden")
	}
	overlay.addEventListener("click", closeModal);
	closeBtn.addEventListener("click", closeModal);
	openButton.addEventListener("click", openModal);
</script>
</body>
</html>