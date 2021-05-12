<template>
	<div class="common_dropdown_contain" id="app">
		<div class="common_dropdown_option">
			<ul class="common_dropdown_option_list">
				<li
					class="common_dropdown_checked"
					id="clickMe"
					@click="orderListVisible = !orderListVisible"
				>
					{{ orderList[selectOrderItem] }}
					<img
						src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-arrow-down-b-512.png"
					/>
				</li>
				<transition
					enter-active-class="animated fadeInDown"
					leave-active-class="animated fadeOutUp"
				>
					<!--         class="list-group"> -->
					<span v-show="orderListVisible">
						<li
							class="common_dropdown_option_item"
							v-for="(item, i) in orderList"
							:key="i"
						>
							<input
								:id="'id' + i"
								type="radio"
								name="order"
								v-model="selectOrderItem"
								:value="i"
							/>
							<label :for="'id' + i" :class="{ on: i === selectOrderItem }">{{
								item
							}}</label>
						</li>
					</span>
				</transition>
			</ul>
		</div>
	</div>
</template>

<script>
import "../../assets/css/DropDown/DropDown.scss";

export default {
	name: "DropDown",
	props: {
		orderList: Array,
	},
	data() {
		return {
			selectOrderItem: 0,
			orderListVisible: false,
			// orderList: [
			//   { name: '디지털개발부', value: '1' },
			//   { name: '금융개발부', value: '2' },
			//   { name: '글로벌개발부', value: '3' },
			//   { name: '기관개발부', value: '4' },
			//   { name: '정보개발부', value: '5' },
			//   { name: 'ICT운영부', value: '6' },
			//   { name: 'ICT기획부', value: '7' },
			// ],
		};
	},
	watch: {
		selectOrderItem: function () {
			this.orderListVisible = false;
			this.$emit("orderItemChange", this.selectOrderItem);
		},
	},
};
</script>
