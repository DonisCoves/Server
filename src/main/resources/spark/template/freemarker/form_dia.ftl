<form role="form" action="/crear_dia" method="POST" id="form_dia">
<button type="submit" class="btn btn-default btn-lg btn-block">Crear Dia</button>
<#assign fila = 0>
	<#include "header_eventos.ftl">
	<#if dia_fiesta??>
		<#list dia_fiesta.eventos?values as evento>
			<#include "fila_evento.ftl">
			<#assign fila = fila + 1>
		</#list>
	</#if>
	<input type="hidden" name="dia_seleccionado" value="${dia_seleccionado}">
	<input type="hidden" name="anyo" value="${anyo}">
	
</form>
	<input type="button" value="Añadir evento" id="add" class="btn btn-default btn-lg btn-block"/>
	
