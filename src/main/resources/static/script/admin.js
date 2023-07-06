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
