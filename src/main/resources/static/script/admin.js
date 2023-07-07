function boardCheck() {
	if ($('#title').val() == "") {
		alert('제목을입력하세요')
		return false
	} else if ($('#content').val() == "") {
		alert('내용을 입력하세요')
		return false
	} else {
		return true
	}
}

function bannerCheck() {
	if ($('#name').val() == "") {
		alert('이미지설명을 적어주세요')
		return false
	} else if ($('#image').val() == "") {
		alert('이미지를 추가해주세요')
		return false
	} else {
		return true
	}
}

function productCheck(productList) {
	$('#formcontent').val($('#content').html())
	
	if ($('#title').val() == "") {
		alert('상품 제목을 작성해주세요')
		return false
	} else if ($('#search').val() == "" || productList.indexOf($('#search').val()) < 1) {
		alert('유효한 카테고리를 입력해주세요')
		return false
	} else if ($('#formcontent').val() == "") {
		alert('상품 설명을 작성해주세요')
		return false
	} else if ($('#price').val() == "") {
		alert('가격을 작성해주세요')
		return false
	} else {
		return true
	}
}


