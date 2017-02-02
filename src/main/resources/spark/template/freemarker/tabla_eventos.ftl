<table class="table table-hover"> 
	<#include "header_eventos.ftl">
	<#if !dia_fiesta??>
		<#list 1..5 as event>
	   		<#include "fila_evento.ftl">
		</#list>
	<#else>
		<#list dia_fiesta.eventos?values as evento>
	   		<#include "fila_evento.ftl">
		</#list>
	</#if>
</table>