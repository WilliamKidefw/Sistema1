/*
* Singleton de Proveedor
*/
var proveedores=[];
class ProveedorService{

	//obtener la lista de proveedores de proveedor
	static get(){
		return new Promise((resolve)=>{

		  	if(proveedores.length==0)
		        $.ajax({
		          url:'/proveedores',
		          method:'get',
		          success:(resp)=>{
		            if(resp.error==0){
		              proveedores=resp.data;
		              resolve(proveedores);
		            }
		          }
		        });
		    else
		    	resolve(proveedores);

		});
	}



	static buscar(valorBusqueda,filtro){
		return new Promise((resolve)=>{
		  	
		        $.ajax({
		          url:'/proveedores',
		          method:'get',
		          data:{
					valorBusqueda,filtro
		          },
		          success:(resp)=>{
		            if(resp.error==0){
		              proveedores=resp.data;
		              resolve(proveedores);
		            }
		          }
		        });
		    

		});
	}
}