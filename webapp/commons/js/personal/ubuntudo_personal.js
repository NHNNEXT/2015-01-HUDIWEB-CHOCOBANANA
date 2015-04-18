/**
 * Created by dahye on 2015. 4. 17..
 */

ubuntudo = {};
ubuntudo.ui = {};

window.addEventListener("load", function () {
    var elList = [];
    elList = document.querySelectorAll("section ul");
    var elLightBox = document.querySelector(".light_box");
    var oDdetailModal = new ubuntudo.ui.detailModal();
    var oModalManager = new ubuntudo.ui.modalManager(oDdetailModal);
    
    [].forEach.call(elList, function(element) {
        element.addEventListener("click", function(ev) {
        oModalManager.modalShow(ev);
    })});
    
    elLightBox.addEventListener("click", function(e) {
        oModalManager.modalHide();
    }); 
});
