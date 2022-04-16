$(() => {
    $("#groupForm").on("submit", function() {
		// 驗證表單輸入欄位填寫及格式
        return verifyFormInput();
    });

	// Description: 驗證表單輸入欄位填寫及格式
	function verifyFormInput() {
		//驗證名稱
         if($("#name").val() == "") {
            alert("請輸入名稱");
            $("#name").focus();
            return false;
        }

	}
})