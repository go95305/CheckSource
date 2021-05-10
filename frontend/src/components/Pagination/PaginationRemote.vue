<template>
	<div class="pagination">
		<!-- 화살표 -->
		<span class="material-icons arrow-icon" @click="MovePageBackward">
			arrow_back
		</span>

		<!-- 첫 페이지 -->
		<span class="pagination-list" v-if="displayFirstNum">
			<input
				class="pagination-input"
				type="radio"
				name="pageList"
				:id="`pagination-first`"
				:value="1"
				v-model="page"
				@click="AlertPageChange(1)"
			/>
			<label :for="`pagination-first`" class="pagination-label"> 1 </label>
		</span>
		<span class="pagination-list" v-if="displayFirstNum">...</span>

		<!-- 페이지 리스트 -->
		<span
			v-for="value in pageList"
			:key="`${value}_pageList`"
			class="pagination-list"
		>
			<input
				class="pagination-input"
				type="radio"
				name="pageList"
				:id="`pagination-${value}`"
				:value="value"
				v-model="page"
				@click="AlertPageChange(value)"
			/>
			<label :for="`pagination-${value}`" class="pagination-label">{{
				value
			}}</label>
		</span>

		<!-- 마지막 페이지 -->
		<span class="pagination-list" v-if="displayLastNum">...</span>
		<span class="pagination-list" v-if="displayLastNum">
			<input
				class="pagination-input"
				type="radio"
				name="pageList"
				:id="`pagination-last`"
				:value="lastPage"
				v-model="page"
				@click="AlertPageChange(lastPage)"
			/>
			<label :for="`pagination-last`" class="pagination-label"
				>{{ lastPage }}
			</label>
		</span>
		<!-- 화살표 -->
		<span class="material-icons arrow-icon" @click="MovePageForward">
			arrow_forward
		</span>
	</div>
</template>
<script>
export default {
	name: "PaginationRemote",
	props: {
		currentPage: Number,
		lastPage: Number,
	},
	data() {
		return {
			displayFirstNum: false,
			displayLastNum: false,
			page: 0,
			pageList: [],
		};
	},
	watch: {
		currentPage: function () {
			this.page = this.currentPage;
		},
		page: function () {
			this.CalculatePageList();
		},
		lastPage: function () {
			this.CalculatePageList();
		},
	},
	created() {
		this.page = this.currentPage;
	},
	methods: {
		CalculatePageList: function () {
			//페이지 목록 연산
			let list = [];
			this.pageList = null;
			if (this.lastPage < 8) {
				//전체 리스트 만들기
				this.displayFirstNum = false;
				this.displayLastNum = false;
				for (let i = 1; i <= this.lastPage; ++i) {
					list.push(i);
				}
			} else {
				//단축 리스트 만들기
				const min = this.page - 2 > 1 ? this.page - 2 : 1;
				const max =
					this.page + 2 < this.lastPage ? this.page + 2 : this.lastPage;

				for (let i = min; i <= max; ++i) {
					list.push(i);
				}
				this.displayFirstNum = list[0] != 1;
				this.displayLastNum = list[list.length - 1] != this.lastPage;
			}
			this.pageList = list;
		},
		MovePageForward: function () {
			if (this.page < this.lastPage) {
				++this.page;
				this.AlertPageChange(this.page);
			}
		},
		MovePageBackward: function () {
			if (this.page > 1) {
				--this.page;
				this.AlertPageChange(this.page);
			}
		},
		AlertPageChange: function (value) {
			if (this.page != value) {
				//router duplicate 방지
				this.$emit("changePage", value);
			}
		},
	},
};
</script>
<style scoped src="@/assets/css/Pagination/PaginationRemote.css"></style>
