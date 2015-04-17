/**
 * Created by dahye on 2015. 4. 17..
 */

ubuntudo = {};
ubuntudo.ui = {};

window.addEventListener("load", function () {
    var elList = document.querySelector(".past");
    var elLightBox = document.querySelector(".light_box");
    var oDdetailModal = new ubuntudo.ui.detailModal(".detail_modal", ".title", ".detail_wrapper", "todo", "tid");
    var oModalManager = new ubuntudo.ui.modalManager(oDdetailModal);
    
    elList.addEventListener("click", function(ev) {
       oModalManager.modalShow(ev);
    });
    
    elLightBox.addEventListener("click", function(e) {
        oModalManager.modalHide();
    }); 
});
