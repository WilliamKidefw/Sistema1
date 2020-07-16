/*
* Singleton de Producto
*/
var productos=[];
class ProductoService{
	//obtener la lista de productos de producto
	static get(){
		return new Promise((resolve)=>{
		  	if(productos.length==0)
		        $.ajax({
		          url:'/productos',
		          method:'get',
		          success:(resp)=>{
		            if(resp.error==0){
		              productos=resp.data;
		              resolve(productos);
		            }
		          }
		        });
		    else
		    	resolve(productos);
		});
	}

	static buscar(valorBusqueda,filtro){
		return new Promise((resolve)=>{		  	
		        $.ajax({
		          url:'/productos',
		          method:'get',
		          data:{
					valorBusqueda,filtro
		          },
		          success:(resp)=>{
		            if(resp.error==0){
		              productos=resp.data;
		              resolve(productos);
		            }
		          }
		        });
		});
	}
}