function getElementsClass(classnames){ 
	var classobj= new Array();//定义数组 
	var classint=0;//定义数组的下标 
	var tags=document.getElementsByTagName("*");//获取HTML的所有标签 
	for(var i in tags){//对标签进行遍历 
		if(tags[i].nodeType==1){//判断节点类型 
			if(tags[i].getAttribute("class") == classnames)//判断和需要CLASS名字相同的，并组成一个数组 
			{ 
				classobj[classint]=tags[i]; 
				classint++; 
			} 
		} 
	} 
	return classobj;//返回组成的数组 
} 
 
var a = getElementsClass("fixed-nav"); 
a[0].style.display = "none";
 
var b = getElementsClass("hot-news"); 
b[0].style.display = "none";
