<#include "cabecera.ftl">

<div class="container">
<div class="page-header">
  <h1>Establece las fechas </h1>
</div> 

	<form role="form" action="/crear_dias" method="POST" data-toggle="validator">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="form-group">
						<label for="titulo" class="control-label">Titulo</label>
						<input type="text" class="form-control" name="titulo" placeholder="ej. Navidad2016 ó Patronales2015" required>
				</div>
			</div>
		</div>
		
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="form-group">
				<label for="descripcion">Descripcion</label>
				<input type="text" class="form-control" name="descripcion" placeholder="ej. Fiestas de Navidad 2016 ó Fiestas Patronales 2015" required>
			</div>
		</div>
	</div>
			
	</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="form-group">
					<label class="control-label" for="fecha_inicio">Fecha inicial Fiestas</label>
					<input type="date" class="form-control"  name="fecha_inicio" required>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="form-group">
					<label for="fecha_final">Fecha final Fiestas</label>
					<input type="date" class="form-control"  name="fecha_final" required>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<button type="submit" class="btn btn-default btn-lg btn-block">Enviar</button>
			</div>
		</div>
	</form>
</div>

<#include "clausura.ftl">
