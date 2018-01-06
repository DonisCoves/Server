<#include "cabecera.ftl">

<#if !dia_seleccionado??>
	<#assign dia_seleccionado = dias[0]>
</#if>


<ul class="nav nav-tabs">
<#-- La primera vez que entramos (por el crear_fiestas)dia_seleccionado no existirá
	Las otras veces la pasaremos por la entrada, recogida como atributo hidden del formulario-->
	<#list dias as dia>
		<#if dia ==dia_seleccionado>
			<#assign dia_seleccionado = dia>
			<li class="active"><a href="${dia}?dia_seleccionado=${dia}">${dia}</a></li>
		<#else>
			<li><a href="${dia}?dia_seleccionado=${dia}">${dia}</a></li>
		</#if>
	</#list>
</ul>

<#include "form_dia.ftl">
<#include "confirmacion.ftl">
<#include "clausura.ftl">