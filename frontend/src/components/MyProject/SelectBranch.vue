<template>
    <div class="select-branch-background">
        <div class="select-branch-container">
            <DropDown
                class="select-branch-dropdown"
                :orderList="branchList"
            ></DropDown>
            <div>
                <button class="select-branch-button cancel-button">취소</button>
                <button class="select-branch-button ok-button">확인</button>
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
    // props: {
    //     branchList: Array,
    // },
    data() {
        return {
            branchList: [
                "master",
                "develop",
                "master",
                "develop",
                "master",
                "develop",
            ],
        };
    },
};
</script>
<style>
.select-branch-background {
    position: fixed !important;
    /* position: absolute !important; */
    width: 100vw;
    height: 100vh;
    background-color: rgba(128, 128, 128, 0.411);
    top: 0;
    left: 0;
    z-index: 1000;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: none;
}

.select-branch-container {
    width: 500px;
    height: 300px;
    background-color: white;
    border-radius: 5px;
    box-shadow: 2px 2px 20px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-flow: column nowrap;
    justify-content: space-evenly;
    align-items: center;
}

.select-branch-dropdown ul {
    width: 400px;
}

.select-branch-dropdown span {
    width: inherit;
}

.select-branch-button {
    width: 130px;
    padding: 10px;
    margin: 0px 5px;
    font-size: large;
    font-weight: 700;
    cursor: pointer;
    border: 1px solid #eee;
    background-color: white;
    border-radius: 5px;
    box-shadow: 0px 4px 5px #ddd;
    transition: all 0.3s ease;
}

.select-branch-button:hover {
    box-shadow: 0px 4px 5px #ccc;
}

.ok-button {
    color: #153081;
}

.cancel-button {
    color: rgb(148, 24, 24);
}
</style>
