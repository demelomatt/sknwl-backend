import { ContentSortType } from "./content-sort-type";
import { ContentType } from "./content-type";

export interface ContentParams {
    pageNumber: number;
    pageSize: number;
    sort?: ContentSortType;
    keyphrase?: string;
    minRatings?: number;
    contentTypes?: ContentType[];
    sourceId?: number;
    languageId?: number;
    minDuration?: number;
    maxDuration?: number;
  }