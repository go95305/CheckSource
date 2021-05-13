<template>
    <div class="select-branch-background">
        <div class="select-branch-container">
            <DropDown
                class="select-branch-dropdown"
                :orderList="branchList"
                @orderItemChange="ChangeBranchItem"
            ></DropDown>
            <div>
                <button
                    class="select-branch-button cancel-button"
                    @click="ClickCancelButton"
                >
                    취소
                </button>
                <button
                    class="select-branch-button ok-button"
                    @click="ClickOKButton"
                >
                    확인
                </button>
            </div>
        </div>
    </div>
</template>
<script>
import DropDown from "../DropDown/DropDown.vue";
export default {
    name: "SelectBranch",
    components: {
        DropDown,
    },
    props: {
        branchList: Array,
    },
    data() {
        return {
            branchValue: "",
        };
    },
    watch: {
        branchList: function () {
            if (this.branchList.length > 0) {
                this.branchValue = this.branchList[0];
                console.log(this.branchValue);
            }
        },
    },
    methods: {
        ChangeBranchItem: function (index) {
            this.branchValue = this.branchList[index];
        },
        ClickOKButton: function () {
            this.$emit("selectOK", this.branchValue);
        },
        ClickCancelButton: function () {
            this.$emit("selectCancel");
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/SelectBranch.css"></style>
