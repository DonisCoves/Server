<div class="container">
	<div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Acceso Administrador</h3>
                </div>
                <div class="panel-body">
                   <form role="form" action="/form-login" method="POST" data-toggle="validator">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus required>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password" required>
                            </div>
                            <button type="submit" class="btn btn-success btn-block" id="login">Login</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>