<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
	opener.frm.imgfilename.value='${image}';  // 전송될 히든태그에 이름 삽입
	opener.document.getElementById('image').innerHTML = '${image}';  // 파일이름 확인을 위한 표시
	/* opener.document.getElementById('previewimg').setAttribute('src', '/upload/' + '${image}' ); */
	/* opener.document.getElementById('previewimg').style.display='inline';   // 이미지 미리보기 */
	opener.document.getElementById('preview').setAttribute('src','/upload/' + '${image}');
	self.close();
</script>

</body>
</html>