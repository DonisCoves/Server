<div class="row">
	<div class="col-md-3">
		<input type="text" class="form-control" name="titulo" value="${(evento.titulo)!}"  required>
	</div> 
    <div class="col-md-7">
		<input type="text" class="form-control" name="descripcion" value="${(evento.descripcion)!}"  required>
	</div> 
    <div class="col-md-1">
		<input type="time" class="form-control" name="hora_inicial" value="${(evento.hora_inicial)!}" required>
	</div> 
    <div class="col-md-1">
		<input type="checkbox" class="form-control " value="${fila}" name="eliminar" />
	</div>
</div>	
    