//<fieldset id="buildyourform">
//<legend>Build your own form!</legend>
//</fieldset>
//<input type="button" value="Preview form" class="add" id="preview" />
//<input type="button" value="Add a field" class="add" id="add" />


$(document).ready(function(){
//	$(".superpuesto").css("display","none");
//	$("a[id=apulsado]").click(function () {
//	$(".superpuesto").css("display","block");
//	});
	$("#add").click(function() {
		var intId = $("#form_dia .row").length ;
		var fila = $("<div class=\"row\" id=\"fila" + intId +  "\"/>");
		var columna1 = $("<div class=\"col-md-3 \" />");
		var columna2 = $("<div class=\"col-md-7 \" />");
		var columna3 = $("<div class=\"col-md-1 \" />");
		var fTitulo = $("<input type=\"text\" class=\"form-control\" name=\"titulo\"  required/>");
		var fDescripcion = $("<input type=\"text\" class=\"form-control\" name=\"descripcion\"  required/>");
		var fHora_inicial = $("<input type=\"time\" class=\"form-control\" name=\"hora_inicial\"  required/>");
		columna1.append(fTitulo);
		columna2.append(fDescripcion);		
		columna3.append(fHora_inicial);
		fila.append(columna1);	
		fila.append(columna2);
		fila.append(columna3);

		$("#form_dia").append(fila);
	});
	
	



});