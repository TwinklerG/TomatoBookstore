import { axios } from "@/api/request";
import { PRODUCT_MODULE, STOKEPILE_MODULE } from "@/api/_prefix";
import type { ResultVO } from "./vo";

export interface ProductInfo {
  id: string;
  title: string;
  price: number;
  rate: number;
  description?: string;
  cover?: string;
  detail?: string;
  specifications?: Specification[];
}

interface Specification {
  id: string;
  item: string;
  value: string;
  productId: string;
}

interface Stockpile {
  id: string;
  productId: string;
  amount: number;
  frozen: number;
}

export const getProducts: () => Promise<ProductInfo[]> = async () => {
  try {
    const resp = await axios.get(`${PRODUCT_MODULE}`);
    const result: ResultVO<ProductInfo[]> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Fetch Products Error: ${error}`);
  }
};

export const getProductById: (id: string) => Promise<ProductInfo> = async (id: string) => {
  try {
    const resp = await axios.get(`${PRODUCT_MODULE}/${id}`);
    const result: ResultVO<ProductInfo> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Fetch Product with ID ${id} Error: ${error}`);
  }
};

export const putProduct: (data: ProductInfo) => Promise<string> = async (data: ProductInfo) => {
  try {
    const resp = await axios.put(`${PRODUCT_MODULE}`, data);
    const result: ResultVO<string> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Update Prodcut Error: ${error}`);
  }
};

export const postProduct: (data: ProductInfo) => Promise<ProductInfo> = async (
  data: ProductInfo
) => {
  try {
    const resp = await axios.post(`${PRODUCT_MODULE}`, data);
    const result = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Add Product Error: ${error}`);
  }
};

export const deleteProduct: (id: string) => Promise<string> = async (id: string) => {
  try {
    const resp = await axios.delete(`${PRODUCT_MODULE}/${id}`);
    const result: ResultVO<string> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Delete Product Error: ${error}`);
  }
};

export const patchStockpile: (productId: string, amount: number) => Promise<string> = async (
  productId: string,
  amount: number
) => {
  try {
    const resp = await axios.patch(`${STOKEPILE_MODULE}/${productId}`, { amount: amount });
    const result: ResultVO<string> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Patch Stockpile Error: ${error}`);
  }
};

export const getStockpileById: (productId: string) => Promise<Stockpile> = async (
  productId: string
) => {
  try {
    const resp = await axios.patch(`${STOKEPILE_MODULE}/${productId}`);
    const result: ResultVO<Stockpile> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Get Stockpile with productID ${productId} Error: ${error}`);
  }
};
