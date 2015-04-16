(function() {
    var MODAL_TEMPLATE = '<div class="detail_modal"><div class="light_box"></div><div class="task"><div class="title"><%=title%></div><div class="detail_wrapper"><div class="due_date"><%=date%></div><textarea class="note"><%=note%></textarea></div><div class="btn_wrapper"><button>edit</button><button>delete</button></div></div></div>';
    
    var ELEMENTS = {
        "BASE":"body",
        "MODAL" : ".detail_modal"
    };
    
    var elBase = document.querySelector(ELEMENTS.BASE);
    _modalShow(elBase, MODAL_TEMPLATE);
    _modalHide(elBase, ELEMENTS.MODAL);
    
    function _modalShow(elBase, sModalTemplate) {
        var body = elBase.innerHTML;
        elBase.innerHTML = sModalTemplate + body;
    }

    function _modalHide(elParent, sModalClassName) {
        var elModal = document.querySelector(sModalClassName);
        elParent.removeChild(elModal);
    }
})();




