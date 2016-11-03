function typeNullFoString(obj){
	if(typeof(obj) == "undefined" || !obj || obj=='undefined' || obj=='null'){
		return '';
	} else {
		return obj;
	}
}