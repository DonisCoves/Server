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
		var columna2 = $("<div class=\"col-md-5 \" />");
		var columna3 = $("<div class=\"col-md-1 \" />");
		var columna4 = $("<div class=\"col-md-1 \" />");
		var columna5 = $("<div class=\"col-md-2\ \" />");
		var fTitulo = $("<input type=\"text\" class=\"form-control\" name=\"titulo\"  required/>");
		var fDescripcion = $("<input type=\"text\" class=\"form-control\" name=\"descripcion\"  required/>");
		var fHora_inicial = $("<input type=\"time\" class=\"form-control\" name=\"hora_inicial\"  required/>");
		var fHora_final = $("<input type=\"time\" class=\"form-control\" name=\"hora_final\"  required/>");
		//var removeButton = $("<input type=\"checkbox\" class=\"form-control\" value="+ intId +" name=\"eliminar\"/>");
//		removeButton.click(function() {
//			if ($(this).hasClass("form-control btn btn-danger active")){ //Si esta pulsado lo "despulsamos"
//				$(this).removeClass("form-control btn btn-danger active");
//				$(this).addClass("form-control btn btn-warning");
//			} else {
//				$(this).removeClass("form-control btn btn-warning");
//				$(this).addClass("form-control btn btn-danger active");
//			}
//
//		});
		columna1.append(fTitulo);
		columna2.append(fDescripcion);		
		columna3.append(fHora_inicial);
		columna4.append(fHora_final);
		//columna5.append(removeButton);
		fila.append(columna1);
		fila.append(columna2);
		fila.append(columna3);
		fila.append(columna4);
		//fila.append(columna5);

		$("#form_dia").append(fila);
	});




});