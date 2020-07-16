/*
* Singleton de Cliente
*/
var clientes=[];
class ClienteService{

	//obtener la lista de clientes de cliente
	static get(){
		return new Promise((resolve)=>{

		  	if(clientes.length==0)
		        $.ajax({
		          url:'/clientes',
		          method:'get',
		          success:(resp)=>{
		            if(resp.error==0){
		              clientes=resp.data;
		              resolve(clientes);
		            }
		          }
		        });
		    else
		    	resolve(clientes);

		});
	}



	static buscar(valorBusqueda,filtro){
		return new Promise((resolve)=>{
		  	
		        $.ajax({
		          url:'/clientes',
		          method:'get',
		          data:{
					valorBusqueda,filtro
		          },
		          success:(resp)=>{
		            if(resp.error==0){
		              clientes=resp.data;
		              resolve(clientes);
		            }
		          }
		        });
		    

		});
	}
}