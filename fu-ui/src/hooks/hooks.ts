import { useDispatch, useSelector } from "react-redux";
import {
    selectVisibleCount,
    selectVisiblePrograms,
} from "../features/search/searchSelectors";
import type { AppDispatch, RootState } from "../store/store";

export const useAppDispatch = useDispatch.withTypes<AppDispatch>();
export const useAppSelector = useSelector.withTypes<RootState>();

export const useVisiblePrograms = () => useAppSelector(selectVisiblePrograms);
export const useVisibleCount = () => useAppSelector(selectVisibleCount);
