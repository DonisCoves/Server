<form role="form" action="/crear_dia" method="POST" id="form_dia">
	<#include "tabla_eventos.ftl">
	<input type="hidden" name="dia_seleccionado" value="${dia_seleccionado}">
	<button type="submit" class="btn btn-default btn-lg btn-block">Crear Dia</button>
</form>
