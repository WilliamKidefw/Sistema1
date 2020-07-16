/*
* Singleton de facturas
*/
var facturas=[];

class FacturaService{
	
	//obtener la lista de facturas de producto
	static get(){
		return new Promise((resolve)=>{
	        if(facturas.length==0)
		        $.ajax({
		          url:'/facturas',
		          method:'get',
		          success:(resp)=>{
		            if(resp.error==0){
		              facturas=resp.data;
		              resolve(facturas);
		            }
		          }
		        });
		    else
		    	resolve(facturas);	 
		});
	}

	static getLastRowIndex(){
		return new Promise((resolve)=>{	        
	        $.ajax({
	          url:'/facturas/ultimo-indice',
	          method:'get',
	          success:(resp)=>{
	            if(resp.error==0){
	              resolve(resp.data[0]);
	            }
	          }
	        });
		});	
	}

}