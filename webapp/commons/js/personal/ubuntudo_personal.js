/**
 * Created by dahye on 2015. 4. 17..
 */
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {
    var oTodoManager = new ubuntudo.ui.todoManager();
    var elList = document.querySelectorAll("section ul");
    var elLightBox = document.querySelector(".light_box");
    var oDetailModal = new ubuntudo.ui.detailModal(oTodoManager);
    var oModalManager = new ubuntudo.ui.modalManager(oDetailModal);
    
    oTodoManager.appendList("/personal");
    
    [].forEach.call(elList, function(element) {
        element.addEventListener("click", function(ev) {
        oModalManager.showModal(ev);
    })});
    
    elLightBox.addEventListener("click", function(e) {
        oModalManager.hideModal();
    }); 
});
