<div class="btn-group">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
    Fiestas Disponibles <span class="caret"></span>
  </button>
  	<ul class="dropdown-menu" role="menu">
	  	<#if uidsFiestas??>
			<#list uidsFiestas as uidFiestas>
	    		<li><a href="/crear_dias?uidFiestas=${uidFiestas}">${uidFiestas}</a></li>
	  		</#list>
	  	</#if>
  	</ul>
</div>