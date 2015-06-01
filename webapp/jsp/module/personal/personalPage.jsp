<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <div id="container" class="ns_personal">
		<aside id = party_icon_list>
			<!-- children will be added here by javascript.-->
		</aside>
		<main>
			<section>
				<div class="category">
					<h1 class="name">Past</h1>
					<span class="date">~ ${yesterday}</span>
				</div>
				<ul class="past">
				</ul>
			</section>
			<section>
				<div class="category">
					<h1 class="name">Today</h1>
					<span class="date">${today}</span>
				</div>
				<ul class="today">
				</ul>
			</section>
			<section>
				<div class="category">
					<h1 class="name">Future</h1>
					<span class="date">${tomorrow} ~</span>
				</div>
				<ul  class="future">
				</ul>
			</section>
		</main>
    </div>
    <!-- build:js /scripts/personal.min.js -->
    <script language="javascript" type="text/javascript" src="/../../../scripts/personal/ubuntudoPersonal.js"></script>
    <!-- endbuild -->
    <!-- build:js /scripts/main.min.js -->
    <script language="javascript" type="text/javascript" src="/../../../scripts/utility.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/dataManager.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/modalManager.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/todoAddModal.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/todoManager.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/detailModal.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/searchManager.js"></script>
    <!-- endbuild -->

</body>
</html>