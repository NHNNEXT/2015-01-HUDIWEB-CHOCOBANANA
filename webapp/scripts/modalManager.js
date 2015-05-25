/**
 * Created by dahye on 2015. 4. 15
 */
ubuntudo.ui.ModalManager = (function() {
	'use strict';

    function ModalManager (oModal) {
        this.modal = oModal;
        this.elModal = oModal.elModal;
    }

    ModalManager.prototype.showModal = function(ev) {
        this.modal.beforeShow(ev);
        this.elModal.style.display = "block";
    };

    ModalManager.prototype.hideModal = function(ev) {
        ev.stopPropagation();
        this.elModal.style.display = "none";
    };

    return ModalManager;
})();
//이거는 즉시실행으로 감싸도 예쁜 것 같다.