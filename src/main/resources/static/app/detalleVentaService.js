/*
* Singleton de DetalleVenta
*/
var detalleVentas=[];
class DetalleVentaService{

	//obtener la lista de detalleVentas de detalleVenta
	static get(){
		return new Promise((resolve)=>{

		  	if(detalleVentas.length==0)
		        $.ajax({
		          url:'/detalleVentas',
		          method:'get',
		          success:(resp)=>{
		            if(resp.error==0){
		              detalleVentas=resp.data;
		              resolve(detalleVentas);
		            }
		          }
		        });
		    else
		    	resolve(detalleVentas);

		});
	}



	static save(detalleVenta,tipoVenta,idVenta){
		return new Promise((resolve)=>{
		  	
		        $.ajax({
		          url:`/${tipoVenta}/${idVenta}/detalle`,
		          method:'post',
		          data:JSON.stringify(detalleVenta),
		          headers: {
		            "content-type": "application/json"
		          },
		          success:(resp)=>{		            
	            	  resolve(resp);
		          },
		          error:(err)=>{
					 resolve({error:1,data:[],message:'error del servidor'});
		          }
		        });
		    

		});
	}
}