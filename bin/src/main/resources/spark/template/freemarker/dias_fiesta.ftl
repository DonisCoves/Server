<#include "cabecera.ftl">

<#if !dia_seleccionado??>
	<#assign dia_seleccionado = titulosDiasFiesta[0]>
</#if>


<ul class="nav nav-tabs">
<#-- La primera vez que entramos (por el crear_fiestas)dia_seleccionado no existirá
	Las otras veces la pasaremos por la entrada, recogida como atributo hidden del formulario-->
	<#list titulosDiasFiesta as tituloDiasFiesta>
		<#assign url = tituloDiasFiesta + anyo>
		<#assign url = url?replace(" ","")>
		<#if tituloDiasFiesta ==dia_seleccionado>
			<#assign dia_seleccionado = tituloDiasFiesta>
			<li class="active"><a href="${url}?dia_seleccionado=${dia_seleccionado}&anyo=${anyo}">${dia_seleccionado}</a></li>
		<#else>
			<li><a href="${url}?dia_seleccionado=${tituloDiasFiesta}&anyo=${anyo}">${tituloDiasFiesta}</a></li>
		</#if>
	</#list>
</ul>

<#include "form_dia.ftl">
<#include "confirmacion.ftl">
<#include "clausura.ftl">