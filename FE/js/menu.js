var ElNav_sub= document.querySelector('.nav__sub');
var ElNav__sub__item = document.querySelectorAll('.nav__sub__item');
var Elsub__item__mega = document.querySelector('.sub__item__mega');
var Elitem__mega__category = document.querySelectorAll('.item__mega__category');
$(document).ready(function(){
    var MenuHandeler = function(){
        const navSubItems = document.querySelectorAll('.nav__sub__item');
        const overlay = document.getElementById('overlay');
        
        navSubItems.forEach(navSubItem => {
            navSubItem.addEventListener('mouseenter', () => {
                overlay.style.display = 'block';
            });
        
            navSubItem.addEventListener('mouseleave', () => {
                overlay.style.display = 'none';
            });
        });
    }
    MenuHandeler();

    var renderMenu = function(rs){

        for(var i=0;i<rs.data.length;i++){
            
            var dom_nav__sub__item = document.createElement('li');
            dom_nav__sub__item.classList.add('nav__sub__item');
            var dom_a = document.createElement('a')
            dom_a.textContent = `${rs.data[i].categoryName}`;
            dom_nav__sub__item.appendChild(dom_a);
            var dom_nav__item__box = document.createElement('div')
            dom_nav__item__box.classList.add('nav__item__box');
            let html2 = ``;
            var dom_sub__item__mega = document.createElement('div');
            var dom_sub__item__mega2 = document.createElement('div');
            var dom_sub__item__mega3 = document.createElement('div');
            let mega_index = 0
            dom_sub__item__mega.classList.add('sub__item__mega',`sub__item__mega-${mega_index+1}`);
            dom_sub__item__mega2.classList.add('sub__item__mega',`sub__item__mega-${mega_index+2}`);
            let html__sub__item__mega2 = 
            `
                <ul class="item__mega__category">
                <p>Sale</p>
                <div class="mega__category__container">
                    <li class="mega__category__item">
                        <div class="img"><img src="/img/quần/23CM.LU002.4_36.png" alt=""></div>
                        <a href="">Tất cả</a>
                    </li>
                    <li class="mega__category__item">
                        <div class="img"><img src="/img/áo/PK001.DEN2.2.avif" alt=""></div>
                        <a href="">Sản phẩm mới</a>
                    </li>
                    <li class="mega__category__item">
                        <div class="img"><img src="/img/quần/mceclip0_105.png" alt=""></div>
                        <a href="">Sale áo</a>
                    </li>
                    <li class="mega__category__item">
                        <div class="img"><img src="/img/thumb_CMM0608_28.png" alt=""></div>
                        <a href="">Sale quần</a>
                    </li>
                </div>
            `;

            let html__sub__item__mega3 = 
            `
                <div class="row-2">
                    <div class="nav__product__left">
                        <div class="product__left_item">
                            <img src="/img/for now/1.png" alt="">
                        </div>
                        <div class="product__left_item">
                            <img src="/img/for now/2.png" alt="">
                        </div>
                    </div>
                    <div class="nav__product__right">
                        <div class="product__right__item">
                            <img src="/img/for now/3.png" alt="">
                        </div>
                    </div>
                </div>
            `;
            dom_sub__item__mega3.classList.add('sub__item__mega',`sub__item__mega-${mega_index+3}`);
            var dom_item__mega__category = document.createElement('ul');
            dom_item__mega__category.classList.add('item__mega__category');
            
            dom_sub__item__mega2.innerHTML = html__sub__item__mega2;
            dom_sub__item__mega3.innerHTML = html__sub__item__mega3;

            for(var j=0;j<rs.data[i].detailCategoryList.length;j++){
                html2 += `
                    <ul class="item__mega__category">
                    
                        <p>${rs.data[i].detailCategoryList[j].categoryName}</p>
                        <li><a href="filterProduct.html?category=${rs.data[i].detailCategoryList[j].categoryName}">Tất cả</a></li>
                        <li><a href="">Sản phẩm mới</a></li>
                    </ul>
                    
                `
            }
            
            dom_sub__item__mega.innerHTML = html2;
            dom_sub__item__mega.appendChild(dom_item__mega__category);
            dom_nav__item__box.appendChild(dom_sub__item__mega);
            dom_nav__item__box.appendChild(dom_sub__item__mega2);
            dom_nav__item__box.appendChild(dom_sub__item__mega3);
            dom_nav__sub__item.appendChild(dom_nav__item__box);

            ElNav_sub.appendChild(dom_nav__sub__item);
        }
    }
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/getAllCategory",
        headers: {
            "Content-Type": "application/json",
        },
        success: function (rs) {
            
            if (rs.succes) {
                
                renderMenu(rs);

            }
        }
    });
    
})
