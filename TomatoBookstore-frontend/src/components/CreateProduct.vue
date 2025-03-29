<script setup lang="ts">
import { ref } from "vue";
import ProductForm from "./ProductForm.vue";
import type { ProductInfo } from "@/api/products";

const product = ref<ProductInfo>({
  id: "",
  title: "",
  price: 0,
  rate: 100,
});
const handleUpdate = (p: ProductInfo) => {
  product.value = p;
};
const handleSubmit = () => {};
const isEditProduct = ref(true);
</script>

<template>
  <ProductForm
    :product="product"
    @update-product="handleUpdate"
    v-model="isEditProduct"
    :handle-submit="handleSubmit"
  />
</template>
