

$(document).ready(function(){
    var linkDetailCategory = "http://localhost:8080/category";
    var token = localStorage.getItem("token")
    $.ajax({
        
        type: "GET",
        url: linkDetailCategory +  "/getAllDetailCategory",
        headers: {
            "Content-Type": "application/json",
        },
        success: function (rs) {
            console.log(rs)
            if(rs.succes){
                
               
                $.each(rs.data,function(index,item){
                    var html =``;
                   
                        if(item.productList.length>0){
                            html+=`
                            <div class="category__title">
                                <h2 class="product__category">${item.categoryName}</h2>
                                <a href="filterProduct.html?category=${item.categoryName}">Xem thêm</a>
                            </div>`;
                           html += `<div class="product__row">`;
                            $.each(item.productList,function(index2,item2){
                                
                                    
                                    html += 
                                    `
                                    <div class="product__item">
                                         <div class="product__item__img">
                                             <a href="detailProduct.html?id=${item2.id}">
                                                 <img src="${linkDetailCategory}/file/${item2.image[0].image}" alt="">
                                             </a>
                                         </div>
                                         <div class="product__item__size">
                                             <div class="active size">Xanh</div>
                                             <div class="size">Đỏ</div>
                                             <div class="size">Nâu</div>
                                         </div>
                                         <div class="product__item__title">
                                             <a href="">${item2.title}</a>
                                         </div>
                                         
                                         <div class="product__item__price">
                                             <p>${item2.priceList[0].price}.000đ</p>
                                         </div>
                                    </div>
                                    `
                                   
                                
                                
                            })
                            html += `</div>`;
                        }

                $('.product').append(html)
                })
               
            }else{
                console.log("Lỗi")
            }
        }
    })

   

 

});


           