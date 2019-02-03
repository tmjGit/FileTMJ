package li.tmj.io;

public class FileTMJ_CompareResultTest {//extends TestCase{
	static class CompareResult {
		enum CompareResultDetail { EXIST_vs_DONT, DONT_vs_EXIST, SIZE_DIFFERS, CONTENT_DIFFERS, TYPE_DIFFERS, IDENTICAL, SAME_CONTENT, SAME_FILE, BOTH_EXIST, BOTH_DONT_EXIST; 

			public boolean isSame() {
				return this==SAME_CONTENT || this==SAME_FILE ||this==BOTH_DONT_EXIST;
			}
		}

		private CompareResultDetail dataFork;
		private CompareResultDetail resourceFork;
		private CompareResultDetail dataSizeBytes;
		private CompareResultDetail resourceSizeBytes;
		//		private CompareResultDetail sizeBytes;
		//		private CompareResultDetail sizeBytes;
		private CompareResultDetail resultDetails;
		private boolean result;

		public CompareResultDetail getDataFork() {
			return dataFork;
		}
		protected void setDataFork(CompareResultDetail resultDetail) {
			this.dataFork = resultDetail;
		}
		public CompareResultDetail getResourceFork() {
			return resourceFork;
		}
		protected void setResourceFork(CompareResultDetail resultDetail) {
			this.resourceFork = resultDetail;
		}
		public CompareResultDetail getDataSizeBytes() {
			return dataSizeBytes;
		}
		protected void setDataSizeBytes(CompareResultDetail resultDetail) {
			this.dataSizeBytes = resultDetail;
		}
		public CompareResultDetail getResourceSizeBytes() {
			return resourceSizeBytes;
		}
		protected void setResourceSizeBytes(CompareResultDetail resultDetail) {
			this.resourceSizeBytes = resultDetail;
		}
		public CompareResultDetail getResultDetails() {
			return resultDetails;
		}
		protected void setResultDetails(CompareResultDetail resultDetail) {
			this.resultDetails = resultDetail;
		}
		public boolean isResult() {
			return result;
		}
		protected void setResult(boolean result) {
			this.result = result;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dataFork == null) ? 0 : dataFork.hashCode());
			result = prime * result + ((dataSizeBytes == null) ? 0 : dataSizeBytes.hashCode());
			result = prime * result + ((resourceFork == null) ? 0 : resourceFork.hashCode());
			result = prime * result + ((resourceSizeBytes == null) ? 0 : resourceSizeBytes.hashCode());
			result = prime * result + (this.result ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompareResult other = (CompareResult) obj;
			if (dataFork != other.dataFork)
				return false;
			if (dataSizeBytes != other.dataSizeBytes)
				return false;
			if (resourceFork != other.resourceFork)
				return false;
			if (resourceSizeBytes != other.resourceSizeBytes)
				return false;
			if (resultDetails != other.resultDetails)
				return false;
			if (result != other.result)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "CompareResult [dataFork=" + dataFork + ", resourceFork=" + resourceFork + ", dataSizeBytes="
					+ dataSizeBytes + ", resourceSizeBytes=" + resourceSizeBytes + ", resultDetails=" + resultDetails + ", result=" + result + "]";
		}


	}







} 


