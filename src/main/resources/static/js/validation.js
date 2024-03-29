// Description: 驗證電話欄為
function validPhone(phone){
	if( phone == "" )
		return { valid:false , message:"請輸入電話" };
	if( isPhoneFormat(phone) )
		return { valid:false , message:"電話必須是手機格式" };
}

// Description: 驗證帳號欄位
function isValidAccount(account) {
	// 確認欄位是否為空
    if(account == "") return {valid: false, message: "請輸入帳號"};
	// 確認是否為Email 格式
    if(!isEmail(account)) return {valid: false, message: "帳號必須是Email 格式"};

	return {valid: true};
}

// Description: 驗證密碼欄位
function isValidPassword(password) {
	// 確認欄位是否為空
    if(password == "") return {valid: false, message: "請輸入密碼"};
	// 確認是否為指定密碼格式
    if(!isPasswordFormat(password)) return {valid: false, message: "密碼必須為6~16為大小寫英文加數字"};

	return {valid: true};
}

// Description: 驗證再次輸入密碼欄位
function isValidCheckPassword(password, checkpassword) {
	// 確認欄位是否為空
	if(checkpassword == "") return {valid: false, message: "請再次輸入密碼"};
	// 確認兩次輸入密碼是否相同
    if(password != checkpassword) return {valid: false, message: "兩次輸入密碼不相符"};

	return {valid: true};
}

// Description: 確認是否為Email 格式
function isEmail(text) {
	var re = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	return re.test(text);
}

// Description: 確認是否為指定密碼格式
function isPasswordFormat(text) {
	var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,16}$/;
	return re.test(text);
}

// Description: 確認是否為身分證字號格式
function isIdNumberFormat(text) {
	var re = /^[A-Z]{1}[0-9]{9}$/;
	return re.test(text);
}

// Description: 確認是否為手機號碼格式
function isPhoneFormat(text) {
	var re = /^09[0-9]{8}$/;
	return re.test(text);
}