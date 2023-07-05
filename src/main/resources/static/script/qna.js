function selectimg(){
	var opt ="toolbar=no,menubar=no,resizable=no,width=450,height=200";
	window.open('selectimg','selecting',opt);
}

function selectedimage(){
	document.frm.submit();
	
}

function passCheck( qna_num){
	var url="passCheck?qna_num="+qna_num;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250,scrollbars=no";
	window.open(url,"passCheck",opt);
}