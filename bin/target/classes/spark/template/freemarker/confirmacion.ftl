<#if exito??>
	<#if exito==true>
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			Dia de fiesta creado
		</div>
	<#else>
		<div class="alert alert-danger alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert">&times;</button>
			  Ha habido un error al crear dia
		</div>
	</#if>
</#if>